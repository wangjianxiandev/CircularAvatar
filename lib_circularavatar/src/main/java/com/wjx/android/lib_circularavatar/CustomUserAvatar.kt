package com.wjx.android.lib_circularavatar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @date: 2020/03/23
 * Time: 11:20
 */
@SuppressLint("AppCompatCustomView")
class CustomUserAvatar constructor(context: Context?, attrs: AttributeSet?= null): ImageView (context, attrs, 0){
    private var mPaintText: Paint? = null
    private var mPaintBackground: Paint? = null
    private var mRect: Rect? = null
    private var mCircleName: String? = "空"
    private var mTextColor = 0
    private var mBackgroundColor = 0
    private var mChineseCount = 0
    private var mEnglishCount = 0
    private var mBackgroundStyle = 0
    private var mShadowRadius = 0f
    private var mIsShowBlurMask: Boolean = false

    init {
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.CustomUserAvatar)
        val backgroundStyle = typedArray.getInteger(R.styleable.CustomUserAvatar_background_style, 0)
        val textColor = typedArray.getInteger(R.styleable.CustomUserAvatar_text_color, R.color.always_white_text)
        val backgroundColor = typedArray.getInteger(R.styleable.CustomUserAvatar_background_color, Color.BLUE)
        val showBlurMask = typedArray.getBoolean(R.styleable.CustomUserAvatar_show_blur_Mask, false)
        val chineseCount = typedArray.getInteger(R.styleable.CustomUserAvatar_chinese_name, 0)
        val englishCount = typedArray.getInteger(R.styleable.CustomUserAvatar_english_name,0)
        this.mBackgroundStyle = backgroundStyle
        this.mTextColor = textColor
        this.mBackgroundColor = backgroundColor
        this.mIsShowBlurMask = showBlurMask
        this.mChineseCount = chineseCount
        this.mEnglishCount = englishCount
        typedArray.recycle()
        init()
    }


    private fun init() {
        mPaintText = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaintBackground = Paint(Paint.ANTI_ALIAS_FLAG)
        mRect = Rect()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 设置背景颜色
        mPaintBackground!!.color = mBackgroundColor
        mPaintBackground!!.style =
            if (mBackgroundStyle == 0) Paint.Style.STROKE else Paint.Style.FILL
        mPaintBackground!!.strokeWidth = 5f
        // 是否绘制发光效果
        if (mIsShowBlurMask) {
            mPaintBackground!!.maskFilter = BlurMaskFilter(10.0f, BlurMaskFilter.Blur.SOLID)
        }
        canvas.drawCircle(
            width / 2.toFloat(),
            width / 2.toFloat(),
            (width - 20) / 2.toFloat(),
            mPaintBackground!!
        )
        // 设置文本大小
        mPaintText!!.textSize = width / 3.toFloat()
        // 设置文本颜色跟随应用主题颜色
        mPaintText!!.color = mTextColor
        // 设置画笔粗细
        mPaintText!!.strokeWidth = 5f
        // 设置阴影半径
        mPaintText!!.setShadowLayer(mShadowRadius, mShadowRadius, mShadowRadius, Color.BLACK)
        // 绘制文字的最小矩形
        mPaintText!!.getTextBounds(mCircleName, 0, 1, mRect)
        val fontMetricsInt = mPaintText!!.fontMetricsInt
        // baseLine上面是负值，下面是正值
        // 所以getHeight()/2-fontMetricsInt.descent 将文本的bottom线抬高至控件的1/2处
        // + (fontMetricsInt.bottom - fontMetricsInt.top) / 2：(fontMetricsInt.bottom - fontMetricsInt.top) 文本的辅助线（top+bottom)/2就是文本的中位线（我是这样理解的）恰好在控件中位线处
        val baseLine =
            height / 2 - fontMetricsInt.descent + (fontMetricsInt.bottom - fontMetricsInt.top) / 2
        // 水平居中
        mPaintText!!.textAlign = Paint.Align.CENTER
        canvas.drawText(mCircleName!!, width / 2.toFloat(), baseLine.toFloat(), mPaintText!!)
    }

    /**
     * 判断一个字符是否是中文
     */
    fun isChineseChar(c: Char): Boolean { // 根据字节码判断
        return c.toInt() >= 0x4E00 && c.toInt() <= 0x9FA5
    }

    /**
     * 判断一个字符串是否含有中文
     *
     * @param str 输入的字符
     * @return
     */
    fun isChineseString(str: String?): Boolean {
        if (str == null) {
            return false
        }
        for (c in str.toCharArray()) {
            if (isChineseChar(c)) {
                return true
            }
        }
        return false
    }

    /**
     * 设置显示的名字
     *
     * @param circleName 要显示的名字
     */
    fun setCircleName(circleName: String) { // 中文名字取后两个
        if (isChineseString(circleName)) {
            mCircleName = if (circleName.length > if (mChineseCount == 0) 2 else mChineseCount) {
                circleName.substring(0, if (mChineseCount == 0) 2 else mChineseCount)
            } else {
                circleName
            }
        } else { // 非中文名字取第一个
            if (circleName.length > if (mEnglishCount == 0) 1 else mEnglishCount) {
                mCircleName = circleName.substring(0, if (mEnglishCount == 0) 1 else mEnglishCount)
                mCircleName = mCircleName!!.toUpperCase()
            } else {
                mCircleName = circleName
                mCircleName = mCircleName!!.toUpperCase()
            }
        }
        invalidate()
    }

    fun setIsShowBlurMask(isShow: Boolean) {
        mIsShowBlurMask = isShow
    }

    fun setShowChineseNameCount(count: Int) {
        mChineseCount = count
    }

    fun setShowEnglishNameCount(count: Int) {
        mEnglishCount = count
    }

    fun setTextColor(color: Int) {
        mTextColor = color
    }

    fun setmBackgroundColor(color: Int) {
        mBackgroundColor = color
    }

    fun setBackgroundStyle(style: Int) {
        mBackgroundStyle = style
    }

    fun setShadowRadius(radius: Float) {
        mShadowRadius = radius
    }
}