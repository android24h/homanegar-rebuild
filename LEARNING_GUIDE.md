# راهنمای یادگیری پروژه‌ی HomaNegar (همانگار)

> این فایل «حافظه‌ی مشترک» ما برای آموزشه. اول هر چت جدید، همین فایل رو بفرست.
> آخر هر گام آپدیت می‌شه: وضعیت گام‌ها + خلاصه و سؤال‌ها و نکته‌های طلایی.

---

## ۱. پروژه در یک نگاه

**HomaNegar** یک اپ اندروید **آفلاین** برای ثبت درآمد روزانه‌ی کسب‌وکارهای کوچکه.

- کاربر در اولین اجرا نوع کسب‌وکارش رو انتخاب می‌کنه (۲۱ نوع + سفارشی).
- هر کسب‌وکار چند **بخش (Section)** داره که یا **خدمات** (SERVICE) هستن یا **کالا** (INVENTORY).
- امکانات: ثبت سریع خدمت، مدیریت قیمت‌ها، فروش کالا و موجودی، داشبورد، گزارش روزانه/ماهانه/سالانه، دوزبانه (فارسی/انگلیسی)، تقویم شمسی/میلادی، تم روشن/تیره.

**تکنولوژی‌ها:** Kotlin · Jetpack Compose · Material 3 · MVVM + Clean Architecture · Room · Hilt · Navigation Compose · Coroutines/Flow · KSP

**پروژه‌ی مرجع:** همون پروژه‌ی اصلی (حدود ۳۸ فایل Kotlin، ۹۵۰۰ خط) + فایل `ARCHITECTURE.md`.
**پروژه‌ی تمرینی:** پروژه‌ی جدیدی که از صفر می‌سازیم و در Git نگه‌داری می‌شه.

---

## ۲. قوانین کار

1. **گام‌به‌گام.** هیچ حدسی زده نمی‌شه و از گام فعلی جلوتر نمی‌ریم. تا علیرضا نگه «بریم بعدی»، گام بعد شروع نمی‌شه.
2. **فقط فایل‌های همون بخش.** در هر گام فقط کلاس‌ها و کدهایی که برای همون قابلیت لازمه آورده می‌شه (Vertical Slice).
3. **فایل‌های تجمیعی تیکه‌تیکه ساخته می‌شن.** مثلاً `Entities.kt`، `Daos.kt`، `Models.kt`، `Strings.kt` در هر گام فقط به اندازه‌ی نیاز همون گام بزرگ می‌شن.
4. **تایپ، نه کپی‌پیست.** کد رو خودم تایپ می‌کنم.
5. **آخر هر گام برنامه باید اجرا بشه.**
6. **آخر هر گام:** خلاصه + سؤال‌های طلایی + نکته‌های طلایی. قبل از رفتن به گام بعد، چند سؤال رو بدون نگاه کردن جواب می‌دم.
7. **هر گام = یک commit در Git.** قالب پیام: `step-01: splash screen`
8. **برای هر یکی دو گام یک چت جدید.** اول چت: این فایل + فایل‌های مرجع همون گام.

---

## ۳. روش آموزش (ترکیبی)

| مرحله | گام‌ها | روش |
|---|---|---|
| ساختن از صفر | ۰ تا ۷ | توضیح مفهوم ← کد با توضیح ← تایپ و اجرا ← سؤال‌ها |
| مرور هدایت‌شده | ۸ تا ۱۲ | خوندن کد با هم + باز کردن عمیق بخش‌های جدید + **تمرین تغییر** روی کد |
| آماده‌سازی رزومه | ۱۳ | رفع باگ، تست، تمیزکاری، انتشار |

**تخمین زمان** (روزی ۲ تا ۳ ساعت): حدود ۵ تا ۶ هفته.

---

## ۴. نقشه‌ی راه و وضعیت

وضعیت: ⬜ شروع نشده · 🔄 در حال انجام · ✅ تمام شده

| گام | عنوان | روش | فایل‌های مرجع اصلی (از پروژه‌ی اصلی) | وضعیت |
|---|---|---|---|---|
| ۰ | اسکلت پروژه | ساختن | `settings.gradle.kts`، `build.gradle.kts` (ریشه و app)، `gradle/libs.versions.toml`، `AndroidManifest.xml`، `di/MyApplication.kt`، `MainActivity.kt`، `res/values/*` | ⬜ |
| ۱ | اسپلش | ساختن | `presentation/splash/SplashScreen.kt`، `core/locale/Strings.kt` (بخش پایه)، `presentation/theme/Theme.kt`، `domain/business/BusinessCatalog.kt` (فقط `BusinessPalette`)، `presentation/app/AppRoot.kt` (NavHost ساده) | ⬜ |
| ۲ | تنظیمات پایه (زبان، تم، تقویم، RTL) | ساختن | `domain/model/Models.kt` (`AppSettings`، `ThemeMode`)، `domain/repository/Repositories.kt` (`SettingsRepository`)، `data/settings/SettingsRepositoryImpl.kt`، `presentation/app/AppViewModel.kt`، `presentation/components/Components.kt` (`LocalAppSettings`)، `core/calendar/AppCalendar.kt` (`CalendarSystem`) | ⬜ |
| ۳ | دیتابیس و Hilt (اولین جدول: Section) | ساختن | `data/local/entity/Entities.kt`، `data/local/dao/Daos.kt`، `data/local/database/AppDatabase.kt`، `data/repository/Mappers.kt`، `data/repository/RepositoryImpls.kt`، `di/Modules.kt`، `Models.kt` (`Section`، `SectionKind`) | ⬜ |
| ۴ | انتخاب کسب‌وکار (Onboarding) | ساختن | `domain/business/BusinessCatalog.kt`، `domain/usecase/UseCases.kt` (`SetupBusinessUseCase`)، جدول `CatalogItem`، `presentation/onboarding/BusinessPickerScreen.kt`، `presentation/theme/AppIcons.kt`، بخش‌های لازم از `Components.kt` | ⬜ |
| ۵ | پوسته‌ی اپ و ناوبری | ساختن | `presentation/app/AppRoot.kt` (Routes، BottomBar، `navigateTab`) | ⬜ |
| ۶ | تاریخ شمسی و فرمت اعداد | ساختن | `core/calendar/AppCalendar.kt` (`Jalali`، `DateKey`، `CalMonth`)، `core/format/Format.kt` | ⬜ |
| ۷ | بخش خدمات (ثبت، ادغام، Undo) | ساختن | جدول `ServiceRecord`، `RecordServiceUseCase`، `presentation/services/ServiceSectionViewModel.kt`، `ServiceSectionScreen.kt`، `ServiceEntrySheet.kt` | ⬜ |
| ۸ | کاتالوگ خدمات و تغییر گروهی قیمت | مرور + تمرین | `presentation/services/CatalogScreen.kt` (+`CatalogViewModel`)، `AdjustCatalogPricesUseCase` | ⬜ |
| ۹ | بخش کالا (محصول، فروش، موجودی) | مرور + تمرین | جدول‌های `Product` و `Sale`، `SaleDao` (تراکنش‌ها)، `presentation/inventory/*` | ⬜ |
| ۱۰ | داشبورد (Home) | مرور + تمرین | `LedgerEntry`، `LedgerRepository`، `presentation/home/*`، `presentation/components/Charts.kt` | ⬜ |
| ۱۱ | گزارش‌ها | مرور + تمرین | `ReportBuilder`، `presentation/reports/*`، `presentation/components/Calendar.kt` | ⬜ |
| ۱۲ | صفحه‌ی تنظیمات و مدیریت بخش‌ها | مرور + تمرین | `presentation/settings/SettingsScreen.kt` (+`SettingsViewModel`)، `DeveloperSection.kt`، `SectionDao.deleteWithData` | ⬜ |
| ۱۳ | آماده‌سازی برای رزومه | پروژه‌ی نهایی | `MIGRATION_1_2`، رفع باگ‌های فصل ۱۰ `ARCHITECTURE.md`، تغییر `com.example`، فعال کردن R8، تست، README، انتشار در GitHub | ⬜ |

**گام فعلی:** ۰ (شروع نشده)

---

## ۵. تصمیم‌های باز

- [ ] **اسم نهایی اپ:** HomaNegar یا همانگار (Hamangar)؟ در کد فعلی «Hamangar» استفاده شده. ← در گام ۰ تصمیم گرفته می‌شه.
- [ ] **package name / applicationId:** به‌جای `com.example.myapplication`. ← در گام ۰.

---

## ۶. شروع هر چت جدید

این متن رو اول چت بفرست، همراه با این فایل و فایل‌های مرجع گام:

```
این راهنمای پروژه‌ی آموزشی HomaNegar هست. طبق قوانین همین فایل پیش برو.
می‌خوایم گام [شماره] رو شروع کنیم. فایل‌های مرجع این گام از پروژه‌ی اصلی رو هم فرستادم.
کد فعلی پروژه‌ی تمرینی‌ام تا آخر گام قبل: [لینک GitHub یا فایل‌ها]
```

---

## ۷. خلاصه‌ها، سؤال‌ها و نکته‌های طلایی

> آخر هر گام، بخش همون گام اینجا اضافه می‌شه.

_(هنوز گامی تمام نشده)_
