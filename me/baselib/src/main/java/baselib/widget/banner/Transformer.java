package baselib.widget.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import baselib.widget.banner.transformer.AccordionTransformer;
import baselib.widget.banner.transformer.BackgroundToForegroundTransformer;
import baselib.widget.banner.transformer.CubeInTransformer;
import baselib.widget.banner.transformer.CubeOutTransformer;
import baselib.widget.banner.transformer.DefaultTransformer;
import baselib.widget.banner.transformer.DepthPageTransformer;
import baselib.widget.banner.transformer.FlipHorizontalTransformer;
import baselib.widget.banner.transformer.FlipVerticalTransformer;
import baselib.widget.banner.transformer.ForegroundToBackgroundTransformer;
import baselib.widget.banner.transformer.RotateDownTransformer;
import baselib.widget.banner.transformer.RotateUpTransformer;
import baselib.widget.banner.transformer.ScaleInOutTransformer;
import baselib.widget.banner.transformer.StackTransformer;
import baselib.widget.banner.transformer.TabletTransformer;
import baselib.widget.banner.transformer.ZoomInTransformer;
import baselib.widget.banner.transformer.ZoomOutSlideTransformer;
import baselib.widget.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
