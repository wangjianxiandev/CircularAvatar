# CircularAvatar
自定义姓名头像，可用于列表用户头像等场景
### 添加使用依赖
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
### 引入项目
```
dependencies {
    implementation 'com.github.wangjianxiandev:CircularAvatar:v1.0.0'
}
```

### 使用方法
```
<com.wjx.android.lib_circularavatar.CustomUserAvatar
        android:id="@+id/avatar_one"
        android:layout_width="110dp"
        android:layout_height="110dp"
        <!--设置头像背景色-->
        app:background_color="@color/background_color"
        <!--设置填充样式 0-不填充 1-填充-->
        app:background_style="0"
        <!--设置字体颜色-->
        app:text_color="@color/black"
        <!--设置中文字符显示数目-->
        app:chinese_name="1"
        <!--设置英文字符显示数目-->
        app:english_name="2"
        <!--设置是否具有发光效果-->
        app:show_blur_Mask="false" />
```
### 展示
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200323123307243.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzM5NDI0MTQz,size_16,color_FFFFFF,t_70)