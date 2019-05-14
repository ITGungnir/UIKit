# UIKit - IconFont

`IconFontView`是一个可以展示`IconFont`字体的控件，它继承自`TextView`，因此具备了所有`TextView`的属性和方法。

## 1、自定义属性
|名称|含义|默认值|
|---|---|---|
|app:ifv_iconFontFace|字体文件（assets目录下的.ttf文件）|iconfont.ttf|

## 2、布局
```xml
<my.itgungnir.ui.icon_font.IconFontView
    android:id="@+id/back"
    android:layout_width="?attr/actionBarSize"
    android:layout_height="?attr/actionBarSize"
    android:background="?android:attr/selectableItemBackground"
    android:text="\u1234"
    android:gravity="center"
    android:textSize="20sp"
    android:textStyle="bold" />
```
**注意：** 建议将所有的`IconFont`放到一个文件中，并命名为`iconfont.ttf`，然后放到项目中的`assets`目录下，否则需要在控件中添加`app:ifv_iconFontFace`属性并指定文件名。