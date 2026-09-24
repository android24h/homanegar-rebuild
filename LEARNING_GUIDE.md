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
- package name: `com.alirezahalvaei.homanegar`
- تنظیمات ساخت: Empty Activity (Compose) · Kotlin · Min SDK 26 · Kotlin DSL
- ریپوی مرجع (پروژه‌ی اصلی): Private، فقط برای مراجعه
- ریپوی تمرینی: همونی که آخر کار (گام ۱۳) نسخه‌ی رزومه می‌شه

---

## ۲. قوانین کار

1. **گام‌به‌گام.** هیچ حدسی زده نمی‌شه و از گام فعلی جلوتر نمی‌ریم. تا علیرضا نگه «بریم بعدی»، گام بعد شروع نمی‌شه.
2. **فقط فایل‌های همون بخش.** در هر گام فقط کلاس‌ها و کدهایی که برای همون قابلیت لازمه آورده می‌شه (Vertical Slice).
3. **فایل‌های تجمیعی تیکه‌تیکه ساخته می‌شن.** مثلاً `Entities.kt`، `Daos.kt`، `Models.kt`، `Strings.kt` در هر گام فقط به اندازه‌ی نیاز همون گام بزرگ می‌شن.
4. **تایپ، نه کپی‌پیست.** کد رو خودم تایپ می‌کنم.
5. **آخر هر گام برنامه باید اجرا بشه.**
6. **آخر هر گام:** خلاصه + سؤال‌های طلایی + نکته‌های طلایی. قبل از رفتن به گام بعد، چند سؤال رو بدون نگاه کردن جواب می‌دم.
7. **Git:** در طول هر گام هر جا لازم باشه commit/push می‌کنیم (Claude می‌گه کجا). پیام‌ها با قالب **Conventional Commits** نوشته می‌شن (بخش ۷ رو ببین) و آخر هر گام یک **tag** زده می‌شه، مثلاً `step-00`.
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
| ۰ | اسکلت پروژه | ساختن | `settings.gradle.kts`، `build.gradle.kts` (ریشه و app)، `gradle/libs.versions.toml`، `AndroidManifest.xml`، `di/MyApplication.kt`، `MainActivity.kt`، `res/values/*` | 🔄 |
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

**گام فعلی:** ۰ — پروژه ساخته شده، `.gitignore` تنظیم شده و اولین push انجام شده. محتوای گام ۰ (فایل‌های gradle و کتابخونه‌ها) هنوز شروع نشده.

---

## ۵. تصمیم‌ها

- [x] **package name / applicationId:** `com.alirezahalvaei.homanegar`
- [x] **اسم اپ:** HomaNegar. در کد مرجع اسم‌هایی مثل `HamangarTheme` هست؛ در پروژه‌ی تمرینی به `HomaNegarTheme` و مشابه تغییر می‌کنن.

---

## ۶. شروع هر چت جدید

این متن رو اول چت بفرست، همراه با این فایل و فایل‌های مرجع گام:

```
این راهنمای پروژه‌ی آموزشی HomaNegar هست. طبق قوانین همین فایل پیش برو.
می‌خوایم گام [شماره] رو شروع کنیم. فایل‌های مرجع این گام از پروژه‌ی اصلی رو هم فرستادم.
کد فعلی پروژه‌ی تمرینی‌ام تا آخر گام قبل: [فایل‌های لازم یا zip پروژه‌ی تمرینی]
```

---

## ۷. Git

### قالب پیام commit (Conventional Commits)

```
نوع(بخش): توضیح کوتاه به انگلیسی، با فعل امری
```

| نوع | کاربرد | مثال |
|---|---|---|
| `feat` | قابلیت جدید | `feat(splash): add animated splash screen` |
| `fix` | رفع باگ | `fix(inventory): prevent negative stock on undo` |
| `refactor` | بهتر کردن کد بدون تغییر رفتار | `refactor(data): extract shared date helpers` |
| `docs` | مستندات | `docs: update learning guide` |
| `chore` | کارهای جانبی و تنظیمات | `chore: initial project setup` |
| `build` | gradle و وابستگی‌ها | `build: add hilt and room dependencies` |
| `test` | تست | `test(calendar): add jalali conversion tests` |

### دستورهایی که یاد گرفتم

| دستور | کارش |
|---|---|
| `git config --global user.name "..."` / `user.email` | معرفی خودم به Git (یک بار روی هر کامپیوتر) |
| `git init` | تبدیل پوشه به مخزن Git (ساخت پوشه‌ی مخفی `.git`) |
| `git status` | نمایش فایل‌های تغییرکرده؛ **هر وقت شک داشتم، این رو می‌زنم** |
| `git add .` | انتخاب همه‌ی تغییرات برای commit بعدی |
| `git commit -m "..."` | ثبت یک «عکس» از پروژه در تاریخچه (فقط روی کامپیوتر خودم) |
| `git branch -M main` | گذاشتن اسم `main` روی شاخه‌ی اصلی |
| `git remote add origin <آدرس>` | معرفی آدرس ریپوی GitHub با اسم مستعار `origin` |
| `git push -u origin main` | فرستادن commitها به GitHub؛ از دفعه‌ی بعد فقط `git push` |

### فایل `.gitignore`

فایلی در ریشه‌ی پروژه که به Git می‌گه چه چیزهایی رو آپلود نکنه: فایل‌های ساختگی و حجیم (`build`، `.gradle`)، فایل‌های شخصی سیستم (`local.properties`) و فایل‌های حساس (کلید امضا `*.jks`).

---

## ۸. خلاصه‌ها، سؤال‌ها و نکته‌های طلایی

> آخر هر گام، بخش همون گام اینجا اضافه می‌شه.

_(هنوز گامی تمام نشده)_
