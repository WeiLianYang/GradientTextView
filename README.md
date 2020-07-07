# GradientTextView

### 用于设置TexView的字体渐变颜色，和动画效果

### 效果预览

![效果动图](image/resource.gif)


#### 一、控件样式

```xml
<declare-styleable name="GradientTextView">
        <attr name="startColor" format="reference|color" />
        <attr name="endColor" format="reference|color" />
        <attr name="direction" format="enum">
            <enum name="leftToRight" value="1" />
            <enum name="topToBottom" value="2" />
        </attr>
        <attr name="translateAnimate" format="boolean" />
        <attr name="translateSpeed" format="enum">
            <enum name="slow" value="20" />
            <enum name="normal" value="10" />
            <enum name="fast" value="5" />
        </attr>
    </declare-styleable>
```

#### 二、属性介绍

| 属性              | 可选值                                    | 作用                            |
|:-----------------|:-----------------------------------------|:-------------------------------|
| startColor       | 值在color.xml中定义                        | 渐变颜色的起始值(默认值：#6200EE)   |
| endColor         | 值在color.xml中定义                        | 渐变颜色的结束值(默认值：#03DAC5)   |
| direction        | leftToRight：从左向右，topToBottom：从上向下 | 渐变颜色的方向(默认值：leftToRight) |
| translateAnimate | true or false                            | 渐变颜色的动画开关(默认值：false)   |
| translateSpeed   | slow、normal、fast                       | 渐变颜色的动画速度(默认值：normal)   |

#### 三、控件在布局中使用

```xml
<com.william.library.GradientTextView
        android:id="@+id/gradientTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/leftToRight_GradientTextView"
        android:textSize="24sp"
        app:startColor="@color/colorAccent"
        app:endColor="@color/colorPrimary"
        app:direction="leftToRight"
        app:translateAnimate="true"
        app:translateSpeed="normal" />
```

#### 四、在代码中控制动画开关

```kotlin
gradientTextView.apply {
    translateAnimate = !translateAnimate
    invalidate()
}
```

