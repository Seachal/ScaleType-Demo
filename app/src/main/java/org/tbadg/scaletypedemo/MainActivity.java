package org.tbadg.scaletypedemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static final int CHOOSE_IMAGE_REQ_CD = 1313;

    // Handles to UI components (made globals for performance):
    private RadioGroup mImageSizeRadioGroup;
    private RadioGroup mScaleTypeRadioGroup1;
    private RadioGroup mScaleTypeRadioGroup2;
    private ImageView mImageView;
    private TextView mDescription;

    // Used to maintain state across configuration changes:
    private int mImageSize;
    private int mScaleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageSizeRadioGroup = findViewById(R.id.image_size);
        mScaleTypeRadioGroup1 = findViewById(R.id.scale_type_rg1);
        mScaleTypeRadioGroup2 = findViewById(R.id.scale_type_rg2);
        mImageView = findViewById(R.id.image_view);
        mDescription = findViewById(R.id.description);

        // Restore the activity state following a configuration change:
        if (savedInstanceState != null) {
            mImageSize = savedInstanceState.getInt("mImageSize");
            mScaleType = savedInstanceState.getInt("mScaleType");
        }

        // To better demonstrate the mScaleType options, force the ImageView to be square:
        setImageViewDimensions();

        // Initialize the GUI with the (possibly null) current state:
        handleImageSizeClick(findViewById(mImageSize));
        handleScaleTypeClick(findViewById(mScaleType));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the activity state upon a configuration change:
        savedInstanceState.putInt("mImageSize", mImageSize);
        savedInstanceState.putInt("mScaleType", mScaleType);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Sets the image view dimensions to be a square that is 3/4 the size of the narrower
     * window dimension.
     */
    private void setImageViewDimensions() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        mImageView.getLayoutParams().width
            = mImageView.getLayoutParams().height = Math.min(size.x, size.y) * 3 / 4;
    }

    /**
     * Handles UI clicks on one of the image-size radio buttons.
     *
     * @param view the item that was selected
     */
    @SuppressWarnings("WeakerAccess")
    public void handleImageSizeClick(View view) {
        // Default to the first radio button (which is probably SMALL):
        if (view == null)
            view = mImageSizeRadioGroup.getChildAt(0);

        RadioButton radioButton = (RadioButton) view;
        Log.v(TAG, "Image size selected: " + radioButton.getText());

        radioButton.setChecked(true);

        mImageSize = view.getId();
        switch (mImageSize) {

            default:
            case R.id.small:
                mImageView.setImageResource(R.drawable.small);
                break;

            case R.id.wide:
                mImageView.setImageResource(R.drawable.wide1);
                break;

            case R.id.tall:
                mImageView.setImageResource(R.drawable.tall);
                break;

            case R.id.user:
                // Create an intent to select an openable image:
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.setType("image/*");
                imageIntent.addCategory(Intent.CATEGORY_OPENABLE);

                // Wrap the intent in a chooser and start it:
                Intent chooserIntent = Intent.createChooser(imageIntent, "Select image");
                startActivityForResult(chooserIntent, CHOOSE_IMAGE_REQ_CD);
                break;
        }

        // Force a re-draw following an image size change:
        mImageView.invalidate();

        // On a config change, we need to wait until the view layout is done
        //   before calculating image matrix dimensions:
        if (mScaleType == R.id.matrix) {
            mImageView.post(new Runnable() {
                @Override
                public void run() {
                    applyMatrix();
                }
            });
        }
    }

    /**
     * Handles UI clicks on one of the scale-type radio buttons.
     *
     * @param view the item that was selected
     */
    @SuppressWarnings("WeakerAccess")
    public void handleScaleTypeClick(View view) {
        // Default to the first radio button (which is probably FIT_CENTER):
        if (view == null)
            view = mScaleTypeRadioGroup1.getChildAt(0);

        RadioButton radioButton = (RadioButton) view;
        Log.v(TAG, "ScaleType selected: " + radioButton.getText());

        // Since we're manually combining 2 RadioGroups to behave as one, we
        //   need to first clear any checks from both groups and then
        //   manually recheck the one that was just clicked:
        mScaleTypeRadioGroup1.clearCheck();
        mScaleTypeRadioGroup2.clearCheck();
        radioButton.setChecked(true);

        mScaleType = view.getId();
        switch (mScaleType) {

            default:
            case R.id.fit_center:
                mDescription.setText(R.string.fit_center_desc);
                mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                break;

            case R.id.fit_start:
                mDescription.setText(R.string.fit_start_desc);
                mImageView.setScaleType(ImageView.ScaleType.FIT_START);
                break;

            case R.id.fit_end:
                mDescription.setText(R.string.fit_end_desc);
                mImageView.setScaleType(ImageView.ScaleType.FIT_END);
                break;

            case R.id.fit_xy:
                mDescription.setText(R.string.fit_xy_desc);
                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                break;

            case R.id.center:
                mDescription.setText(R.string.center_desc);
                mImageView.setScaleType(ImageView.ScaleType.CENTER);
                break;

            case R.id.center_crop:
                mDescription.setText(R.string.center_crop_desc);
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;

            case R.id.center_inside:
                mDescription.setText(R.string.center_inside_desc);
                mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;

            case R.id.matrix:
                mDescription.setText(R.string.matrix_desc);
                mImageView.setScaleType(ImageView.ScaleType.MATRIX);
                applyMatrix();
                break;
        }
    }

    /**
     * Applies image matrix transformation(s) onto the image view.
     */
    @SuppressWarnings("PointlessArithmeticExpression")
    private void applyMatrix() {
        Matrix matrix = new Matrix();

        // Get the current dimensions of the whole available area:
        int ivWidth = mImageView.getWidth();
        int ivHeight = mImageView.getHeight();

        // Get the current dimensions of the actual image as displayed:
        int bmWidth = mImageView.getDrawable().getIntrinsicWidth();
        int bmHeight = mImageView.getDrawable().getIntrinsicHeight();

        Log.v(TAG, String.format("\nbmWidth=%d, bmHeight=%d, ivWidth=%d, ivHeight=%d",
                                 bmWidth, bmHeight, ivWidth, ivHeight));

        logMatrix("Default matrix", matrix);

        // Calculate matching source and destination points and apply a mapping
        //   polygon that will scale the image a la FitXY, but also decrease the
        //   length the top side, giving a trapezoidal:
        float[] src = {0, 0, 0, bmHeight, bmWidth, bmHeight, bmWidth, 0};
        float[] dst = {ivWidth * 3 / 10, 0, 0, ivHeight, ivWidth, ivHeight, ivWidth * 7 / 10, 0};

        // An alternate polygonal mapping:
        //float[] dst = {ivWidth * 1/2, 0, 0, ivHeight, ivWidth * 7/8, ivHeight, ivWidth, ivHeight * 1/4};

        matrix.setPolyToPoly(src, 0, dst, 0, 4);
        logMatrix("After polygonal mapping", matrix);

        // Add a rotation to the matrix:
        matrix.postRotate(90.0f, ivWidth / 2.0f, ivHeight / 2.0f);
        logMatrix("After rotate", matrix);

        // Apply the calculated matrix to the image view:
        mImageView.setImageMatrix(matrix);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_IMAGE_REQ_CD) {
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Image chosen: " + data.getData());
                mImageView.setImageURI(data.getData());
            }
        }
    }

    private void logMatrix(String label, Matrix matrix) {

        float[] matrixVals = new float[9];
        matrix.getValues(matrixVals);
        Log.i(TAG, String.format(
            "%s:  matrix = %s\n  scale (x,y) = (%f, %f)\n  translate (x, y) = (%f, %f)\n  skew "
            + "(x,y) = (%f, %f)\n  perspective-0 = %f\n  perspective-1 = %f\n  perspective-2 = %f",
            label, matrix, matrixVals[Matrix.MSCALE_X], matrixVals[Matrix.MSCALE_Y],
            matrixVals[Matrix.MSKEW_X], matrixVals[Matrix.MSKEW_Y],
            matrixVals[Matrix.MTRANS_X], matrixVals[Matrix.MTRANS_Y],
            matrixVals[Matrix.MPERSP_0], matrixVals[Matrix.MPERSP_1], matrixVals[Matrix.MPERSP_2]));
    }
}
