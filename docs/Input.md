# UIKit - Input

`Input`是`UIKit`包提供的一系列控件组，主要用于表单页面，目前包括以下几种控件：
* `PasswordInput`：用于密码输入，可以切换`显示密码`/`隐藏密码`；
* `ProgressButton`：带进度条的按钮；
* `ShadowInput`：带背景色的文本输入框，可以清除文本；
* `TextInput`：普通的文本输入框，可以清除文本。

## 1、PasswordInput
`PasswordInput`中的自定义属性如下：

|名称|含义|默认值|
|---|---|---|
|app:pi_hint|文本框中的hint文本|空字符串|
|app:pi_hintColor|文本框中hint文本的颜色|Color.LTGRAY|
|app:pi_textColor|文本框中文本的颜色|Color.DKGRAY|
|app:pi_showIcon|显示密码图标|空字符串|
|app:pi_hideIcon|隐藏密码图标|空字符串|
|app:pi_iconColor|清空按钮的颜色|Color.DKGRAY|
|app:pi_dividerColor|分隔线的颜色|Color.LTGRAY|

一个示例的布局代码如下：
```xml
<my.itgungnir.ui.input.PasswordInput
    android:id="@+id/passwordInput"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:layout_marginTop="30dp"
    app:pi_dividerColor="@color/clr_divider"
    app:pi_hideIcon="\ue8d5"
    app:pi_hint="请输入密码"
    app:pi_hintColor="@color/clr_divider"
    app:pi_iconColor="@color/clr_text"
    app:pi_showIcon="\ue8d4"
    app:pi_textColor="@color/clr_text" />
```

使用时，可以通过`passwordInput.getInput()`方法获取到控件中的`EditText`控件。

## 2、ProgressButton
`ProgressButton`中的自定义属性如下：

|名称|含义|默认值|
|---|---|---|
|app:pb_enabledColor|可点击状态下的背景色|Color.DKGRAY|
|app:pb_disabledColor|不可点击状态下的背景色|Color.LTGRAY|
|app:pb_enabledTextColor|可点击状态下的文本颜色|Color.WHITE|
|app:pb_disabledTextColor|不可点击状态下的文本颜色|Color.DKGRAY|
|app:pb_cornerRadius|按钮的圆角半径|3dp|

一个示例的布局代码如下：
```xml
<my.itgungnir.ui.input.ProgressButton
    android:id="@+id/progressButton"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:layout_marginStart="10dp"
    android:layout_marginTop="60dp"
    android:layout_marginEnd="10dp"
    app:pb_cornerRadius="5dp"
    app:pb_disabledColor="@color/clr_divider"
    app:pb_disabledTextColor="@color/clr_text"
    app:pb_enabledColor="@color/colorAccent"
    app:pb_enabledTextColor="@color/clr_text" />
```

使用时，可以通过调用控件提供的不同方法来为控件设置不同的状态，这些方法包括：
* `disabled(text)`：使按钮处于不可点击的状态，并设置其文本；
* `loading()`：使按钮处于正在加载的状态，此时隐藏按钮文本，显示圆形进度条，不可点击；
* `ready(text)`：使按钮处于可点击状态，并设置其文本。

## 3、ShadowInput
`ShadowInput`中的自定义属性如下：

|名称|含义|默认值|
|---|---|---|
|app:si_hint|文本框中的hint文本|空字符串|
|app:si_hintColor|文本框中hint文本的颜色|Color.LTGRAY|
|app:si_textColor|文本框中文本的颜色|Color.DKGRAY|
|app:si_iconFont|清空按钮的图标|空字符串|
|app:si_iconColor|清空按钮的颜色|Color.DKGRAY|

一个示例的布局代码如下：
```xml
<my.itgungnir.ui.input.ShadowInput
    android:id="@+id/shadowInput"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:layout_marginTop="30dp"
    android:background="@color/clr_divider"
    app:si_hint="请输入手机号"
    app:si_hintColor="@color/clr_background"
    app:si_iconColor="@color/clr_background"
    app:si_iconFont="\ue6f2"
    app:si_textColor="@color/clr_background" />
```

使用时，可以通过`shadowInput.getInput()`方法获取到控件中的`EditText`控件。

## 4、TextInput
`TextInput`中的自定义属性如下：

|名称|含义|默认值|
|---|---|---|
|app:ti_hint|文本框中的hint文本|空字符串|
|app:ti_hintColor|文本框中hint文本的颜色|Color.LTGRAY|
|app:ti_textColor|文本框中文本的颜色|Color.DKGRAY|
|app:ti_iconFont|清空按钮的图标|空字符串|
|app:ti_iconColor|清空按钮的颜色|Color.DKGRAY|
|app:ti_dividerColor|分隔线的颜色|Color.LTGRAY|

一个示例的布局代码如下：
```xml
<my.itgungnir.ui.input.TextInput
    android:id="@+id/textInput"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:layout_marginTop="10dp"
    app:ti_dividerColor="@color/clr_divider"
    app:ti_hint="请输入用户名"
    app:ti_hintColor="@color/clr_divider"
    app:ti_iconColor="@color/clr_text"
    app:ti_iconFont="\ue6f2"
    app:ti_textColor="@color/clr_text" />
```

使用时，可以通过`textInput.getInput()`方法获取到控件中的`EditText`控件。