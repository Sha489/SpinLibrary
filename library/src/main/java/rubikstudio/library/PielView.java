package rubikstudio.library;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;

import rubikstudio.library.model.LuckyItem;

/**
 * Created by kiennguyen on 11/5/16.
 */

public class PielView extends View {
    private RectF mRange = new RectF();
    private int mRadius;

    private Paint mArcPaint;
    private Paint mBackgroundPaint;
    private TextPaint mTextPaint;
    private TextPaint mCurrencyPaint;

    private float mStartAngle = 0;
    private int mCenter;
    private int mPadding;
    private int mTopTextPadding;
    private int mTopTextSize;
    private int mSecondaryTextSize;
    private int mRoundOfNumber = 4;
    private int mEdgeWidth = -1;
    private boolean isRunning = false;

    private int borderColor = 0;
    private int defaultBackgroundColor = 0;
    private int mOverlayColor = 0;
    private boolean showOverlay = false;
    private Drawable drawableCenterImage;
    private int textColor = 0;

    private int predeterminedNumber = -1;
    private int targetIndex;

    float viewRotation;
    double fingerRotation;
    long downPressTime, upPressTime;
    double newRotationStore[] = new double[3];

    private Canvas canvas;

    private List<LuckyItem> mLuckyItemList;

    private PieRotateListener mPieRotateListener;

    Resources r = getContext().getResources();

    public interface PieRotateListener {
        void rotateDone(int index);
    }

    public PielView(Context context) {
        super(context);
    }

    public PielView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPieRotateListener(PieRotateListener listener) {
        this.mPieRotateListener = listener;
    }

    private void init() {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setDither(true);

        mTextPaint = new TextPaint();
        mCurrencyPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mCurrencyPaint.setAntiAlias(true);

//        if (textColor != 0) mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14,
                getResources().getDisplayMetrics()));
        mCurrencyPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5,
                getResources().getDisplayMetrics()));

        mRange = new RectF(mPadding, mPadding, mPadding + mRadius, mPadding + mRadius);
    }

    public int getLuckyItemListSize() {
        return mLuckyItemList.size();
    }

    public void setData(List<LuckyItem> luckyItemList) {
        this.mLuckyItemList = luckyItemList;
        invalidate();
    }

    public void setPieBackgroundColor(int color) {
        defaultBackgroundColor = color;
        invalidate();
    }

    public void setOverlayColor(int color) {
        mOverlayColor = color;
        invalidate();
    }

    public void setBorderColor(int color) {
        borderColor = color;
        invalidate();
    }

    public void setTopTextPadding(int padding) {
        mTopTextPadding = padding;
        invalidate();
    }


    public void setPieCenterImage(Drawable drawable) {
        drawableCenterImage = drawable;
        invalidate();
    }

    public void setTopTextSize(int size) {
        mTopTextSize = size;
        invalidate();
    }

    public void setSecondaryTextSizeSize(int size) {
        mSecondaryTextSize = size;
        invalidate();
    }


    public void setBorderWidth(int width) {
        mEdgeWidth = width;
        invalidate();
    }

//    public void setPieTextColor(int color) {
//        textColor = color;
//        invalidate();
//    }

    private void drawPieBackgroundWithBitmap(Canvas canvas, Bitmap bitmap) {
        canvas.drawBitmap(bitmap, null, new Rect(mPadding / 2, mPadding / 2,
                getMeasuredWidth() - mPadding / 2,
                getMeasuredHeight() - mPadding / 2), null);
    }

    /**
     * @param canvas
     */
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        if (mLuckyItemList == null) {
            return;
        }

        drawBackgroundColor(canvas, defaultBackgroundColor);

        init();

        float tmpAngle = mStartAngle;
        float sweepAngle = 360f / mLuckyItemList.size();

        for (int i = 0; i < mLuckyItemList.size(); i++) {

            float startAngle = tmpAngle;
            float endAngle = tmpAngle + sweepAngle;

            float gradientStartAngle = startAngle + 90;
            float gradientEndAngle = endAngle + 90;

            float startX = mCenter + (float) Math.cos(Math.toRadians(gradientStartAngle)) * mRadius;
            float startY = mCenter + (float) Math.sin(Math.toRadians(gradientStartAngle)) * mRadius;
            float endX = mCenter + (float) Math.cos(Math.toRadians(gradientEndAngle)) * mRadius;
            float endY = mCenter + (float) Math.sin(Math.toRadians(gradientEndAngle)) * mRadius;

            Shader sliceGradient = new LinearGradient(
                    startX, startY, endX, endY,
                    new int[]{mLuckyItemList.get(i).gradientColor1, mLuckyItemList.get(i).gradientColor2, mLuckyItemList.get(i).gradientColor3, mLuckyItemList.get(i).gradientColor4},
                    null,
                    Shader.TileMode.CLAMP
            );

            if (mLuckyItemList.get(i).gradientColor1 != 0) {
                mArcPaint.setStyle(Paint.Style.FILL);
                mArcPaint.setShader(sliceGradient);
                canvas.drawArc(mRange, tmpAngle, sweepAngle, true, mArcPaint);

                mArcPaint.setShader(null);
            }

//            drawSliceBackgroundImage(canvas, tmpAngle, null);
            drawInnerArcImage(canvas, tmpAngle, mLuckyItemList.get(i).arrowImage);


            if (borderColor != 0 && mEdgeWidth > 0) {
                mArcPaint.setStyle(Paint.Style.STROKE);
                mArcPaint.setColor(borderColor);
                mArcPaint.setStrokeWidth(mEdgeWidth);
                canvas.drawArc(mRange, tmpAngle, sweepAngle, true, mArcPaint);
            }

            int sliceColor = mLuckyItemList.get(i).color != 0 ? mLuckyItemList.get(i).color : defaultBackgroundColor;

            if (!TextUtils.isEmpty(mLuckyItemList.get(i).topText))
                drawTopText(canvas, tmpAngle, sweepAngle, mLuckyItemList.get(i).topText, sliceColor);
            if (!TextUtils.isEmpty(mLuckyItemList.get(i).secondaryText))
                drawSecondaryText(canvas, tmpAngle, mLuckyItemList.get(i).secondaryText, sliceColor, mLuckyItemList.get(i).textColor,mLuckyItemList.get(i).topReward);

            if (mLuckyItemList.get(i).icon != null)
                drawImage(canvas, tmpAngle, mLuckyItemList.get(i).icon);
            tmpAngle += sweepAngle;

//            if (textColor == 0)
//                mTextPaint.setColor(mLuckyItemList.get(i).textColor);
        }

//        if(showOverlay) {
//            drawOverlayColor(canvas, mOverlayColor);
//        }

//        drawCenterImage(canvas, drawableCenterImage);

        for (int i = 0; i < mLuckyItemList.size(); i++) {

        }
    }

    private void drawBackgroundColor(Canvas canvas, int color) {
        if (color == 0)
            return;
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(color);
        canvas.drawCircle(mCenter, mCenter, mCenter - 5, mBackgroundPaint);
    }

    private void drawOverlayColor(Canvas canvas, int color) {

        float tmpAngle = mStartAngle;
        float sweepAngle = 360f / mLuckyItemList.size();

        for (int i = 0; i < mLuckyItemList.size(); i++) {

            if (targetIndex != i) {

                if (mLuckyItemList.get(i).gradientColor1 != 0) {
                    Paint overlayPaint = new Paint();
                    overlayPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.black));
                    overlayPaint.setAlpha(150);
                    canvas.drawArc(mRange, tmpAngle, sweepAngle, true, overlayPaint);

                    tmpAngle += sweepAngle;
                }
            } else {
                mArcPaint.setStyle(Paint.Style.STROKE);
                mArcPaint.setColor(Color.parseColor("#e0c964"));
                mArcPaint.setStrokeWidth(12);
                canvas.drawArc(mRange, tmpAngle, sweepAngle, true, mArcPaint);
                tmpAngle += sweepAngle;
            }
        }
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());

        mPadding = getPaddingLeft() == 0 ? 10 : getPaddingLeft();
        mRadius = width - mPadding * 2;

        mCenter = width / 2;

        setMeasuredDimension(width, width);
    }

    /**
     * @param canvas
     * @param tmpAngle
     * @param bitmap
     */
    private void drawImage(Canvas canvas, float tmpAngle, Bitmap bitmap) {
        int imgWidth = mRadius / mLuckyItemList.size();

        float angle = (tmpAngle + 360f / mLuckyItemList.size() / 2);
        float radian = (float) (angle * Math.PI / 180);

        float distanceFactor = 0.23f;  // 0.0 = center, 1.0 = edge
        float distance = mRadius * distanceFactor;

        int x = (int) (mCenter + distance * Math.cos(radian));
        int y = (int) (mCenter + distance * Math.sin(radian));

        Rect rect = new Rect(x - imgWidth / 5, y - imgWidth / 5,
                x + imgWidth / 5, y + imgWidth / 5);

        canvas.save();

        canvas.rotate(angle + 90, rect.centerX(), rect.centerY());

        canvas.drawBitmap(bitmap, null, rect, null);

        canvas.restore();
    }

    private void drawInnerArcImage(Canvas canvas, float tmpAngle, Bitmap bitmap) {
        int imgWidth = mRadius / mLuckyItemList.size();

        float angle = (tmpAngle + 360f / mLuckyItemList.size() / 2);
        float radian = (float) (angle * Math.PI / 180);

        int x = 0, y = 0;
        Rect rect = null;

        if(mLuckyItemList.size() == 8) {

            float distanceFactor = 2f;
            float distance = (mRadius / 2 / 11) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 0.9f;   // keep width normal
            float heightScale = 1.5f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

//        int x = (int) (mCenter + mRadius / 2 / 13 * Math.cos(radian));
//        int y = (int) (mCenter + mRadius / 2 / 13 * Math.sin(radian));

//        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2,
//                x + imgWidth / 2, y + imgWidth / 2);
        } else if(mLuckyItemList.size() == 7) {

            float distanceFactor = 2.3f;
            float distance = (mRadius / 2 / 11) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 1.1f;   // keep width normal
            float heightScale = 1.5f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

        } else if(mLuckyItemList.size() == 6) {

            float distanceFactor = 1f;
            float distance = (mRadius / 2 / 5) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 1.1f;   // keep width normal
            float heightScale = 1.3f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

        } else if(mLuckyItemList.size() == 5) {

            float distanceFactor = 2f;
            float distance = (mRadius / 2 / 10) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 1.1f;   // keep width normal
            float heightScale = 1.0f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

        } else if(mLuckyItemList.size() == 4) {

            float distanceFactor = 2f;
            float distance = (mRadius / 2 / 11) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 1.1f;   // keep width normal
            float heightScale = 0.8f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

        } else if(mLuckyItemList.size() == 3) {

            float distanceFactor = 2f;
            float distance = (mRadius / 2 / 11) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 1.4f;   // keep width normal
            float heightScale = 0.6f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

        } else if(mLuckyItemList.size() == 2) {

            float distanceFactor = 1f;
            float distance = (mRadius / 2 / 11) * distanceFactor;

            x = (int) (mCenter + distance * Math.cos(radian));
            y = (int) (mCenter + distance * Math.sin(radian));

            float widthScale = 1.1f;   // keep width normal
            float heightScale = 0.3f;  // double the height

            int halfWidth = (int) (imgWidth * widthScale / 2);
            int halfHeight = (int) (imgWidth * heightScale / 2);

            rect = new Rect(
                    x - halfWidth,
                    y - halfHeight,
                    x + halfWidth,
                    y + halfHeight
            );

        }

        canvas.save();

        Paint paint = new Paint();
        paint.setAlpha(50);

        canvas.rotate(angle + 90, rect.centerX(), rect.centerY());

        canvas.drawBitmap(bitmap,null, rect, paint);

        canvas.restore();
    }

    private void drawCenterImage(Canvas canvas, Drawable drawable) {
        ;
        Bitmap bitmap = LuckyWheelUtils.drawableToBitmap(drawable);
        bitmap = Bitmap.createScaledBitmap(bitmap, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), false);
        canvas.drawBitmap(bitmap, getMeasuredWidth() / 2 - bitmap.getWidth() / 2,
                getMeasuredHeight() / 2 - bitmap.getHeight() / 2, null);
    }

    private boolean isColorDark(int color) {
        double colorValue = ColorUtils.calculateLuminance(color);
        double compareValue = 0.30d;
        return colorValue <= compareValue;
    }


    /**
     * @param canvas
     * @param tmpAngle
     * @param sweepAngle
     * @param mStr
     */
    private void drawTopText(Canvas canvas, float tmpAngle, float sweepAngle, String mStr, int backgroundColor) {
        Path path = new Path();
        path.addArc(mRange, tmpAngle, sweepAngle);

//       mTextPaint.setColor(textColor);

//        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNowDisplay-Bold.otf");
        mTextPaint.setTypeface(typeface);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTopTextSize);
        float textWidth = mTextPaint.measureText(mStr);
        int hOffset = (int) (mRadius * Math.PI / mLuckyItemList.size() / 2 - textWidth / 2);

        int vOffset = mTopTextPadding;
        canvas.drawTextOnPath(mStr, path, hOffset, vOffset, mTextPaint);
    }

    /**
     * @param canvas
     * @param tmpAngle
     * @param mStr
     * @param backgroundColor
     */
    private void drawSecondaryText(Canvas canvas, float tmpAngle, String mStr, int backgroundColor, int textColor, boolean isTopReward) {
        canvas.save();
        int arraySize = mLuckyItemList.size();

//        if (textColor == 0)
            mTextPaint.setColor(textColor);
            mCurrencyPaint.setColor(textColor);

//
//        if (backgroundColor == -1) {
//            mTextPaint.setColor(Color.parseColor("#000000"));
//        } else {
//            mTextPaint.setColor(Color.parseColor("#FFDE88"));
//        }

//        if (textColor == 0)
//            mTextPaint.setColor(Color.parseColor("#000000"));
//        else
//            mTextPaint.setColor(textColor);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int height = displayMetrics.heightPixels;
//        int width = displayMetrics.widthPixels;

//        Typeface typeface = Typeface.create(Typeface.SERIF, Typeface.BOLD);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNowDisplay-Bold.otf");
        mTextPaint.setTypeface(typeface);

//        if(getHeight() <= 320 && getWidth() <= 320){
//            mTextPaint.setTextSize(35);
//        } else if(getHeight() > 320 && getHeight() <= 650 && getWidth() > 320 && getWidth() <=650) {
//            mTextPaint.setTextSize(40);
//        } else if(getHeight() > 650 && getHeight() <= 1000 && getWidth() > 650 && getWidth() <= 1000){
//            mTextPaint.setTextSize(40);
//        } else {
        if(mStr.length() < 6) {
            mTextPaint.setTextSize(Math.round(
                    TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 14f,r.getDisplayMetrics())));
        } else {
            mTextPaint.setTextSize(Math.round(
                    TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 12f,r.getDisplayMetrics())));
        }

        if(isTopReward){
            //mTextPaint.setTextSize(65);
            mTextPaint.setTextSize(Math.round(
                    TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 14f,r.getDisplayMetrics())));

        }

        mTextPaint.setTextAlign(Paint.Align.CENTER);

        float textWidth = mTextPaint.measureText(mStr);

        float initFloat = (tmpAngle + 360f / arraySize / 2);
        float angle = (float) (initFloat * Math.PI / 180);

        int x = (int) (mCenter + mRadius / 2 / 1.7 * Math.cos(angle));
        int y = (int) (mCenter + mRadius / 2 / 1.7 * Math.sin(angle));

//        RectF rect = new RectF(x + textWidth, y,
//                x - textWidth, y);
//
//        Path path = new Path();
//        path.addRect(rect, Path.Direction.CW);
//        path.close();

        canvas.rotate(initFloat + (arraySize / 18f) + 90, x, y);

        if(mStr.length() > 13) {
            mTextPaint.setTextSize(Math.round(
                    TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 10f,r.getDisplayMetrics())));
            String kept = mStr.substring(0, 13);
            canvas.drawText(kept, x, + y, mTextPaint);
            y += mTextPaint.descent() - mTextPaint.ascent();

            String remainder = mStr.substring(13, mStr.length());

            canvas.drawText(remainder, x, y, mTextPaint);
        } else {
            if(isTopReward) {
                y += mTextPaint.descent() - mTextPaint.ascent() - 100;
            } else {
                y += mTextPaint.descent() - mTextPaint.ascent() - 50;
                x += mTextPaint.descent() - mTextPaint.ascent() - 25;

                y += mCurrencyPaint.descent() - mCurrencyPaint.ascent() - 50;
                x += mCurrencyPaint.descent() - mCurrencyPaint.ascent() - 25;
            }

            Typeface typeface1 = Typeface.createFromAsset(getContext().getAssets(), "fonts/HelveticaNowDisplay-Bold.otf");
            mCurrencyPaint.setTypeface(typeface1);
            mCurrencyPaint.setTextSize(Math.round(
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 7f,r.getDisplayMetrics())));
//            String currency = "SAR";
            canvas.drawText(mStr, x , y , mTextPaint);
//            if(mStr.length() == 2) {
//                canvas.drawText(currency, x + 30, y - 5, mCurrencyPaint);
//            } else if(mStr.length() == 3) {
//                canvas.drawText(currency, x + 40, y - 5, mCurrencyPaint);
//            } else if(mStr.length() == 4) {
//                canvas.drawText(currency, x + 55, y - 5, mCurrencyPaint);
//            } else if(mStr.length() == 5) {
//                canvas.drawText(currency, x + 65, y - 5, mCurrencyPaint);
//            } else {
//                canvas.drawText(currency, x + 40, y - 5, mCurrencyPaint);
//            }
        }

//        for (String line: mStr.split(" ", 2)) {
//            canvas.drawText(line, x, y, mTextPaint);
//
//        }

//        canvas.drawTextOnPath(mStr, path, mTopTextPadding / 1f, mTextPaint.getTextSize() / 10f, mTextPaint);
        canvas.restore();
    }

    /**
     * @return
     */
    private float getAngleOfIndexTarget(int index) {
        return (360f / mLuckyItemList.size()) * index;
    }

    /**
     * @param numberOfRound
     */
    public void setRound(int numberOfRound) {
        mRoundOfNumber = numberOfRound;
    }


    public void setPredeterminedNumber(int predeterminedNumber) {
        this.predeterminedNumber = predeterminedNumber;
    }

    public void rotateTo(final int index) {
        targetIndex = index;
        Random rand = new Random();
        rotateTo(index, SpinRotation.CLOCKWISE, true);
    }

    /**
     * @param index
     * @param rotation,  spin orientation of the wheel if clockwise or counterclockwise
     * @param startSlow, either animates a slow start or an immediate turn based on the trigger
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    public void rotateTo(final int index, @SpinRotation final int rotation, boolean startSlow) {
        if (isRunning) {
            return;
        }

        int rotationAssess = rotation <= 0 ? 1 : -1;

        //If the staring position is already off 0 degrees, make an illusion that the rotation has smoothly been triggered.
        // But this inital animation will just reset the position of the circle to 0 degreees.
        if (getRotation() != 0.0f) {
            setRotation(getRotation() % 360f);
            TimeInterpolator animationStart = startSlow ? new AccelerateInterpolator() : new LinearInterpolator();
            //The multiplier is to do a big rotation again if the position is already near 360.
            float multiplier = getRotation() > 200f ? 2 : 1;
            animate()
                    .setInterpolator(animationStart)
                    .setDuration(500L)
                    .setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            isRunning = true;
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isRunning = false;
                            setRotation(0);
                            rotateTo(index, rotation, false);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                        }
                    })
                    .rotation(360f * multiplier * rotationAssess)
                    .start();
            return;
        }

        // This addition of another round count for counterclockwise is to simulate the perception of the same number of spin
        // if you still need to reach the same outcome of a positive degrees rotation with the number of rounds reversed.
        if (rotationAssess < 0) mRoundOfNumber++;

        float targetAngle = ((360f * mRoundOfNumber * rotationAssess) + 270f - getAngleOfIndexTarget(index) - (360f / mLuckyItemList.size()) / 2);
        animate()
                .setInterpolator(new DecelerateInterpolator())
                .setDuration(mRoundOfNumber * 1000 + 100L)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        isRunning = true;
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isRunning = false;
                        setRotation(getRotation() % 360f);
                        if (mPieRotateListener != null) {
                            mPieRotateListener.rotateDone(index);
                            showOverlay = true;
                            invalidate();
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                })
                .rotation(targetAngle)
                .start();
    }

    public boolean touchEnabled = true;

    public boolean isTouchEnabled() {
        return touchEnabled;
    }

    public void setTouchEnabled(boolean touchEnabled) {
        this.touchEnabled = touchEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isRunning || !touchEnabled) {
            return false;
        }

        final float x = event.getX();
        final float y = event.getY();

        final float xc = getWidth() / 2.0f;
        final float yc = getHeight() / 2.0f;

        double newFingerRotation;


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                viewRotation = (getRotation() + 360f) % 360f;
                fingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));
                downPressTime = event.getEventTime();
                return true;
            case MotionEvent.ACTION_MOVE:
                newFingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));

                if (isRotationConsistent(newFingerRotation)) {
                    setRotation(newRotationValue(viewRotation, fingerRotation, newFingerRotation));
                }
                return true;
            case MotionEvent.ACTION_UP:
                newFingerRotation = Math.toDegrees(Math.atan2(x - xc, yc - y));
                float computedRotation = newRotationValue(viewRotation, fingerRotation, newFingerRotation);

                fingerRotation = newFingerRotation;

                // This computes if you're holding the tap for too long
                upPressTime = event.getEventTime();
                if (upPressTime - downPressTime > 700L) {
                    // Disregarding the touch since the tap is too slow
                    return true;
                }

                // These operators are added so that fling difference can be evaluated
                // with usually numbers that are only around more or less 100 / -100.
                if (computedRotation <= -250f) {
                    computedRotation += 360f;
                } else if (computedRotation >= 250f) {
                    computedRotation -= 360f;
                }

                double flingDiff = computedRotation - viewRotation;
                if (flingDiff >= 200 || flingDiff <= -200) {
                    if (viewRotation <= -50f) {
                        viewRotation += 360f;
                    } else if (viewRotation >= 50f) {
                        viewRotation -= 360f;
                    }
                }

                flingDiff = computedRotation - viewRotation;

                if (flingDiff <= -60 ||
                        //If you have a very fast flick / swipe, you an disregard the touch difference
                        (flingDiff < 0 && flingDiff >= -59 && upPressTime - downPressTime <= 200L)) {
                    if (predeterminedNumber > -1) {
                        rotateTo(predeterminedNumber, SpinRotation.COUNTERCLOCKWISE, false);
                    } else {
                        rotateTo(getFallBackRandomIndex(), SpinRotation.COUNTERCLOCKWISE, false);
                    }
                }

                if (flingDiff >= 60 ||
                        //If you have a very fast flick / swipe, you an disregard the touch difference
                        (flingDiff > 0 && flingDiff <= 59 && upPressTime - downPressTime <= 200L)) {
                    if (predeterminedNumber > -1) {
                        rotateTo(predeterminedNumber, SpinRotation.CLOCKWISE, false);
                    } else {
                        rotateTo(getFallBackRandomIndex(), SpinRotation.CLOCKWISE, false);
                    }
                }

                return true;
        }
        return super.onTouchEvent(event);
    }

    private float newRotationValue(final float originalWheenRotation, final double originalFingerRotation, final double newFingerRotation) {
        double computationalRotation = newFingerRotation - originalFingerRotation;
        return (originalWheenRotation + (float) computationalRotation + 360f) % 360f;
    }

    private int getFallBackRandomIndex() {
        Random rand = new Random();
        return rand.nextInt(mLuckyItemList.size() - 1) + 0;
    }

    /**
     * This detects if your finger movement is a result of an actual raw touch event of if it's from a view jitter.
     * This uses 3 events of rotation temporary storage so that differentiation between swapping touch events can be determined.
     *
     * @param newRotValue
     */
    private boolean isRotationConsistent(final double newRotValue) {
        double evalValue = newRotValue;

        if (Double.compare(newRotationStore[2], newRotationStore[1]) != 0) {
            newRotationStore[2] = newRotationStore[1];
        }
        if (Double.compare(newRotationStore[1], newRotationStore[0]) != 0) {
            newRotationStore[1] = newRotationStore[0];
        }

        newRotationStore[0] = evalValue;

        if (Double.compare(newRotationStore[2], newRotationStore[0]) == 0
                || Double.compare(newRotationStore[1], newRotationStore[0]) == 0
                || Double.compare(newRotationStore[2], newRotationStore[1]) == 0

                //Is the middle event the odd one out
                || (newRotationStore[0] > newRotationStore[1] && newRotationStore[1] < newRotationStore[2])
                || (newRotationStore[0] < newRotationStore[1] && newRotationStore[1] > newRotationStore[2])
        ) {
            return false;
        }
        return true;
    }


    @IntDef({
            SpinRotation.CLOCKWISE,
            SpinRotation.COUNTERCLOCKWISE
    })
    @interface SpinRotation {
        int CLOCKWISE = 0;
        int COUNTERCLOCKWISE = 1;
    }

}
