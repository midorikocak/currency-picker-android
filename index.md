<h1 align="center">Currency Picker for Android</h1>
<p align="center">
   <a href="https://jitpack.io/#midorikocak/currency-picker-android"> <img src="https://jitpack.io/v/midorikocak/currency-picker-android.svg" /></a>
  <a href="https://travis-ci.org/midorikocak/currency-picker-android"> <img src="https://travis-ci.org/midorikocak/currency-picker-android.svg?branch=master" /></a>
  <a href="https://www.paypal.me/midorikocak"> <img src="https://img.shields.io/badge/paypal-donate-yellow.svg" /></a>
  <br /><br />CurrencyPicker is a simple library that can be show a currency picker. See the example to see more detail.
</p>


<img src="https://raw.githubusercontent.com/midorikocak/currency-picker-android/master/screenshot.png" width="480" height="800" />

## How to use

Integrating the project is simple a refined all you need to do is follow the below steps

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
Step 2. Add the dependency
```java
dependencies {
        compile 'com.github.midorikocak:currency-picker-android:1.1.6'
}
```

Once the project has been added to gradle the user can implement this with easy.

```java
CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");
picker.show(getSupportFragmentManager(), "CURRENCY_PICKER");
picker.setListener(new CurrencyPickerListener() {
    @Override
    public void onSelectCurrency(String name, String code, String dialCode, int flagDrawableResID) {
        // Implement your code here
    }
});
```

That's it your all done.

### Get user currency based on code

The following code will get the current users currency details based on code.

```java
CurrencyPicker picker = CurrencyPicker.newInstance("Select Currency");
ExtendedCurrency currency = picker.getUserCurrencyInfo(code);
//TODO use the currency object
```
