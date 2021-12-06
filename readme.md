

ImageView 的几种模式，

一般使用 cent_crop 模式，可以适应大部分情况具有白边的图片，缺点是图片会被裁剪。

![图片](https://github.com/Seachal/ScaleType-Demo/blob/master/media/Jietu20200110-151655.png)

fixXY ，当图片尺寸 和  iv  刚匹配时， 才不会被拉伸。




-------
网络上写ImageView scaleType的很多，但是详细说明centerInside和fitCenter区别的基本没有。相信不少人跟我有一样的困惑。

其实两者的区别很简单。fitxx开头的都是填充视图的意思，当视图View的大小比图片小时两者使用的效果是一样；而当View大小比图片大时，fitCenter会保持原图比例放大图片去填充View，而CenterInside会保持原图大小。

这时有些同学可能就有疑问了：那CenterInside不就和Center效果一样了吗？

其实不是的。

Center是会保持原图大小，而CenterInside图片的大小是不会超过View的大小的。

用个简单的表示式的话就会很清楚：

Center:图片大小=原始图片大小；

fitCenter:图片大小=View大小；

CenterInside:图片大小<=View大小&&图片大小<=原始图片大小；

现在是不是很清晰了呢？ ^-^


#  adjustViewBounds

------
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

也就是说，adjustViewBounds 可以根据 ImageView 的宽高和 ScaleType 自动调整实际图片资源的[宽高比](https://www.zhihu.com/search?q=%E5%AE%BD%E9%AB%98%E6%AF%94&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra=%7B%22sourceType%22%3A%22article%22%2C%22sourceId%22%3A149874009%7D) ，保持与原有图片同样的尺寸比例，这在[全屏图片](https://www.zhihu.com/search?q=%E5%85%A8%E5%B1%8F%E5%9B%BE%E7%89%87&search_source=Entity&hybrid_search_source=Entity&hybrid_search_extra=%7B%22sourceType%22%3A%22article%22%2C%22sourceId%22%3A149874009%7D) 的应用上十分有用。

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

参考:



--


##  感觉是关于 adjust 解析的最清楚的了。

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