

##  ImageView 的几种模式，

* CENTER；
* CENTER_CROP；
* CENTER_INSIDE；
* FIT_CENTER；
* FIT_END；
* FIT_START；
* FIT_XY；
* MATRIX；



###    CENTER

    > Center the image in the view, but **perform no scaling**

    > 在不缩放的前提下，将 Image 的中间显示在 ImageView 的中间

    *   Image 尺寸比 ImageView 尺寸大
        *   只显示 Image 中间与 ImageView 尺寸相等的部分
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 完整地显示在 ImageView 中间

    
###   CENTER\_CROP

    > Scale the image uniformly (maintain the image's aspect ratio) so that both dimensions (width and height) of the image will be **equal to or larger than** the corresponding dimension of the view (minus padding). The image is then centered in the view.

    > Image Width Height 等比例缩放至至少一个方向上 Image 与 ImageView 尺寸一样（Image 短边缩放至与 ImageView 对应边相等（Width 对应 Width，Height 对应 Height）），之后将缩放后的 Image 的中间显示在 ImageView 的中间

    *   Image 尺寸比 ImageView 尺寸大
        *   Image 短边缩小至与 ImageView 对应边相等，Image 长边根据相应的缩放系数进行缩放，之后将 Image 中间显示在 ImageView 中间。
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 短边放大至与 ImageView 对应边相等，Image 长边根据相应的缩放系数进行缩放，之后将 Image 中间显示在 ImageView 中间。

   
###  CENTER\_INSIDE

    > Scale the image uniformly (maintain the image's aspect ratio) so that both dimensions (width and height) of the image will be **equal to or less than** the corresponding dimension of the view (minus padding). The image is then centered in the view.

    > 完整地将 Image 显示在 ImageView 中间。如果 Image 比 ImageView 尺寸大，则将长边缩放至与 ImageView 对应边相等，同时，短边根据缩放系数缩放。之后，将缩放后的 Image 完整地显示在 ImageView 上；如果 Image 比 ImageView 尺寸小，直接将 Image 显示在 ImageView 中间。

    *   Image 尺寸比 ImageView 尺寸大
        *   Image 长边缩小至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 中间。
    *   Image 尺寸与 Image View 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 不进行任何处理，直接显示在 ImageView 中间。

    
###   FIT\_CENTER

    > Compute a scale that will maintain the original src aspect ratio, but will also ensure that **src fits entirely inside dst**. **At least one axis (X or Y) will fit exactly.** The result is centered inside dst.

    > 完整地将 Image 显示在 ImageView 中间。如果 Image 比 ImageView 尺寸大，则将长边缩放至与 ImageView 对应边相等，同时，短边根据缩放系数缩放，之后将缩放后的 Image 完整地显示在 ImageView 上；如果 Image 比 ImageView 尺寸小，则将长边放大至与 ImageView 对应边相等，之后将缩放后的 Image 完整地显示在 ImageView 上。总之，在保证 Image 完整显示在 ImageView 里面的前提下，保证在一个方向上，Image 与 ImageView 是相等的。

    *   Image 尺寸比 ImageView 尺寸大
        *   Image 长边缩小至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 中间。
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 长边放大至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 中间。

  
###   FIT\_END

    > Compute a scale that will maintain the original src aspect ratio, but will also ensure that src fits entirely inside dst. At least one axis (X or Y) will fit exactly. **END aligns the result to the right and bottom edges of dst.**

    > **完整地将 Image 显示在 ImageView 右边或下边。** 如果 Image 比 ImageView 尺寸大，则将长边缩放至与 ImageView 对应边相等，同时，短边根据缩放系数缩放，之后将缩放后的 Image 完整地显示在 ImageView 右边或下边；如果 Image 比 ImageView 尺寸小，则将长边放大至与 ImageView 对应边相等，之后将缩放后的 Image 完整地显示在 ImageView 右边或下边。总之，在保证 Image 完整显示在 ImageView 里面的前提下，保证在一个方向上，Image 与 ImageView 是相等的。

    *   Image 尺寸比 ImageView 尺寸大
        *   Image 长边缩小至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 右边或下边。
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 长边放大至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 右边或下边。

    
###   FIT\_START

    > Compute a scale that will maintain the original src aspect ratio, but will also ensure that src fits entirely inside dst. At least one axis (X or Y) will fit exactly. **START aligns the result to the left and top edges of dst.**

    > **完整地将 Image 显示在 ImageView 左边或上边。** 如果 Image 比 ImageView 尺寸大，则将长边缩放至与 ImageView 对应边相等，同时，短边根据缩放系数缩放，之后将缩放后的 Image 完整地显示在 ImageView 左边或上；如果 Image 比 ImageView 尺寸小，则将长边放大至与 ImageView 对应边相等，之后将缩放后的 Image 完整地显示在 ImageView 左边或上边。总之，在保证 Image 完整显示在 ImageView 里面的前提下，保证在一个方向上，Image 与 ImageView 是相等的。

    *   Image 尺寸比 ImageView 尺寸大
        *   Image 长边缩小至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 左边或上边。
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 长边放大至与 ImageView 对应边相等，Image 短边根据相应的缩放系数进行缩放，之后将 Image 显示在 ImageView 左边或上边。

    
###   FIT\_XY

    > Scale in X and Y independently, so that src matches dst exactly. This may change the aspect ratio of the src.

    > **完整地将 Image 显示在 ImageView 里面。** Image X、Y 方向上分别缩放至与 ImageView 对应边相等，Image 的 Width 和 Height 缩放系数可以不一致。

    *   Image 尺寸比 ImageView 尺寸大
        *   Image 的 Width、Height 分别缩放至与 ImageView 对应边相等，之后将 Image 完整地显示在 ImageView 里面。
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 的 Width、Height 分别缩放至与 ImageView 对应边相等，之后将 Image 完整地显示在 ImageView 里面。

  
###   MATRIX

    > Scale using the image matrix when drawing.

    > 将 Image 从 ImageView 左上角开始显示。

    *   Image 尺寸比 ImageView 尺寸大
        *   ImageView 只显示 Image 中从左上角开始与 ImageView 尺寸相等的部分，多余的地方不显示。
    *   Image 尺寸与 ImageView 尺寸相等
        *   完整显示
    *   Image 尺寸比 ImageView 尺寸小
        *   Image 显示在 ImageView 的左上角。



cent_crop 模式可以适应部分情况具有白边的图片，缺点是图片会被裁剪。
fixXY ，当图片尺寸 和  iv  刚匹配时， 才不会被拉伸。
![图片](https://github.com/Seachal/ScaleType-Demo/blob/master/media/Jietu20200110-151655.png)



------



## centerInside和fitCenter区别
网络上写ImageView scaleType的很多，但是详细说明centerInside和fitCenter区别的基本没有。相信不少人跟我有一样的困惑。

其实两者的区别很简单。fitxx开头的都是填充视图的意思，当视图View的大小比图片小时两者使用的效果是一样；而当View大小比图片大时，fitCenter会保持原图比例放大图片去填充View，而CenterInside会保持原图大小。

这时有些同学可能就有疑问了：那CenterInside不就和Center效果一样了吗？

其实不是的。

Center是会保持原图大小，而CenterInside图片的大小是不会超过View的大小的。

用个简单的表示式的话就会很清楚：

Center:图片大小=原始图片大小；

fitCenter:图片大小=View大小；

CenterInside:图片大小<=View大小&&图片大小<=原始图片大小；


------

##   adjustViewBounds


首先来看一下 ImageView 的属性 adjustViewBounds 的定义：

```text
/**
 * Set this to true if you want the ImageView to adjust its bounds
 * to preserve the aspect ratio of its drawable.
 *
 * <p><strong>Note:</strong> If the application targets API level 17 or lower,
 * adjustViewBounds will allow the drawable to shrink the view bounds, but not grow
 * to fill available measured space in all cases. This is for compatibility with
 * legacy {@link android.view.View.MeasureSpec MeasureSpec} and
 * {@link android.widget.RelativeLayout RelativeLayout} behavior.</p>
 *
 * @param adjustViewBounds Whether to adjust the bounds of this view
 * to preserve the original aspect ratio of the drawable.
 *
 * @see #getAdjustViewBounds()
 *
 * @attr ref android.R.styleable#ImageView_adjustViewBounds
 */
@android.view.RemotableViewMethod
public void setAdjustViewBounds(boolean adjustViewBounds) {
    mAdjustViewBounds = adjustViewBounds;
    if (adjustViewBounds) {
        setScaleType(ScaleType.FIT_CENTER);
    }
}


如果您希望 ImageView 调整其边界以保留其可绘制对象的纵横比，则将此设置为 true。
注意：如果应用程序的目标是 API 级别 17 或更低，adjustViewBounds 将允许 drawable 缩小视图边界，但在所有情况下都不会增长以填充可用的测量空间。 这是为了与旧的MeasureSpec和RelativeLayout行为兼容。

参数：
adjustViewBounds – 是否调整此视图的边界以保留可绘制对象的原始纵横比。
也可以看看：
getAdjustViewBounds()
```

也就是说，adjustViewBounds 可以根据 ImageView 的宽高和 ScaleType 自动调整实际图片资源的[宽高比]保持与原有图片同样的尺寸比例，这在[全屏图片]的应用上十分有用。

比如，要在一个 scaleType 为 fitXY（拉升原图，填满 ImageView） 的 ImageView 上保持宽高比，并且图片的宽度保持和屏幕宽度一致（match\_parent），则使用 android:adjustViewBounds 即可实现：

```text
<ImageView
        android:id="@+id/iv_pay_qrcode_open_guid_bg"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:adjustViewBounds="true"
        android:scaleType="fitXY" />
```

需要注意的是，要保持图片宽度和屏幕宽度一致，需要使用宽高比小于屏幕宽高比的图片资源文件。



------


##  关于 adjust 解析的最清楚的了。

取值为true时：

Adjust the ImageView's bounds to preserve the aspect ration of its drawable.

调整ImageView的界限来保持图像纵横比不变。

这并不意味着ImageView的纵横比就一定和图像的纵横比相同

XML定义里的android:adjustViewBounds="true"会将这个ImageView的scaleType设为fitCenter。不过这个fitCenter会被后面定义的scaleType属性覆盖（如果定义了的话），除非在Java代码里再次显示调用setAdjustViewBounds(true)。

如果设置的layout\_width与layout\_height都是定值，那么设置adjustViewBounds是没有效果的，ImageView将始终是设定的定值的宽高。

如果设置的layout\_width与layout\_height都是wrap\_content,那么设置adjustViewBounds是没有意义的，因为ImageView将始终与图片拥有相同的宽高比（但是并不是相同的宽高值，通常都会放大一些）。

如果两者中一个是定值，一个是wrap\_content，比如layout\_width="100px",layout\_height="wrap\_content"时，ImageView的宽将始终是100px，而高则分两种情况：

（1）当图片的宽小于100px时，layout\_height将与图片的高相同，即图片不会缩放，完整显示在ImageView中，ImageView高度与图片实际高度相同。图片没有占满ImageView，ImageView中有空白。

（2）当图片的宽大于等于100px时，此时ImageView将与图片拥有相同的宽高比，因此ImageView的layout\_height值为：100除以图片的宽高比。比如图片是500X500的，那么layout\_height是100。图片将保持宽高比缩放，完整显示在ImageView中，并且完全占满ImageView。

----

