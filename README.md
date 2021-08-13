# MXSkinChange 动态换肤插件
[![](https://jitpack.io/v/com.gitee.zhangmengxiong/MXSkinChange.svg)](https://jitpack.io/#com.gitee.zhangmengxiong/MXSkinChange)

```gradle
    implementation 'com.gitee.zhangmengxiong:MXSkinChange:1.0.6'
```
插件深度绑定 `AppCompatActivity` `Lifecycle`，Activity打开和结束自动释放对View的引用
项目原理：
使用 `LayoutInflaterCompat.setFactory2(layoutInflater, factory)` 方法，对Activity创建View的过程进行注入，对系统View/ViewGroup替换为自定义View/ViewGroup，自定义View会对相关的资源引用进行资源名替换引用

## 用法
#### 1：Application 中需要初始化一次
```kotlin
MXSkinManager.init(this)
```
#### 2：在Activity中绑定Factory
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    MXSkinManager.attach(lifecycle, layoutInflater)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
}
```

#### 3：定义新皮肤资源文件
> 这里假设皮肤名称：**dark**

- 在主工程 src/main 新建资源目录 res-dark
- build.gradle中创建引用新资源目录
```xml
android {
    sourceSets {
        main {
            res.srcDirs = ['src/main/res', 'src/main/res-dark']
        }
    }
}
```
- 新资源目录中定义相关资源，新皮肤的所有资源需要加上后缀 `_dark`
  示例：
  R.drawable.bg_launch_btn  -> R.drawable.bg_launch_btn **_dark**
  
  R.color.color_red_text  -> R.color.color_red_text **_dark**

- 加载新皮肤

```kotlin
// 加载新皮肤 ：dark
MXSkinManager.loadSkin("dark")

// 恢复默认皮肤：
MXSkinManager.resetSkin()
```

------------


## 如何适配自定义View
插件并不能识别自定义的View，需要手动注入或继承自相对应的View

#### 如何自定义一个继承自系统View的自定义View
- 如：`LinearLayout/RelativeLayout/FrameLayout/TextView/ImageView/****`
- 自定义View可以继承 **MXSkin**+xxx 类，

示例：自定义一个继承自LinearLayout的View，只需要继承自 **MXSkinLinearLayout** 就可以
```kotlin
class ColorLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MXSkinLinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_color, this, true)
    }
	
	*****
	
	// 返回TRUE时，皮肤变更时会刷新这个View的相关资源引用
    override fun needObserved(): Boolean  = true
}
```
类似 **MXSkinLinearLayout** 的View还有：
- MXSkinLinearLayout::class.java
- MXSkinRelativeLayout::class.java
- MXSkinFrameLayout::class.java
- MXSkinTextView::class.java
- MXSkinImageView::class.java
- MXSkinView::class.java
- MXSkinButton::class.java
- MXSkinScrollView::class.java
- MXSkinHorizontalScrollView::class.java
- MXSkinEditText::class.java
- MXSkinRadioButton::class.java
- MXSkinRadioGroup::class.java
- MXSkinCheckBox::class.java
- MXSkinCheckTextView::class.java
- MXSkinProgressBar::class.java
- MXSkinRatingBar::class.java
- MXSkinSeekBar::class.java
- MXSkinViewGroup::class.java
- MXSkinCardView::class.java
- MXSkinConstraintLayout::class.java
- MXSkinRecyclerView::class.java

#### 如何自定义一个第三方View

- 先定义一个View
```kotlin
class CView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : androidx.appcompat.widget.AppCompatTextView(context, attrs), ISkinView {
    private var colorResId = BaseAttr.INVALID_ID

    init {
        val a: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CView,
            0, 0
        )
        try {
            colorResId = BaseAttr.getResourceId(a, R.styleable.CView_color)
        } finally {
            a.recycle()
        }

        onSkinChange()
    }

    override fun getName(): String {
        // 这里返回这个View在xml中定义的名字
        return CView::class.java.name
    }

    override fun getSelfView(): View {
        return this
    }

    override fun onSkinChange() {
        // 皮肤更新
        if (colorResId != BaseAttr.INVALID_ID) {
            val color = MXSkinResource.getColorStateList(context, colorResId)
            setTextColor(color)
        }
    }
}
```

- 注册这个View
  注意：需要在MXSkinManager.attach(lifecycle, layoutInflater)前进行注册，否则无法识别
```kotlin
MXSkinViewRegister.register(CView::class.java)
```

- 调用`MXSkinManager.loadSkin("dark")`换肤完成