# Pocket Finance Lite

**EN:** An Android expense tracker built with Jetpack Compose, MVVM/UDF, and Hilt (offline-first; Room coming next).  
**VI:** Ứng dụng quản lý chi tiêu cá nhân (thu/chi) xây bằng Jetpack Compose, MVVM/UDF và Hilt (ưu tiên offline; sẽ thêm Room ở các commit tiếp theo).

## Features
- Add / edit / delete transactions (WIP)
- Monthly summary (WIP)
- Fixed categories: Food, Transport, Bills, Shopping, Health, Entertainment, Other (planned)

## Tech stack
- Kotlin
- Jetpack Compose + Material 3
- Navigation Compose
- Hilt (Dependency Injection)

## Architecture (high level)
**EN:** MVVM + Unidirectional Data Flow (UDF): UI emits events → ViewModel updates state → UI renders state.  
**VI:** MVVM + UDF: UI gửi event → ViewModel xử lý và cập nhật state → UI render theo state.

## Getting started
### Requirements
- Android Studio (latest stable)
- JDK 11+

### Run
1. Open the project in Android Studio
2. Sync Gradle
3. Run the `app` configuration on an emulator/device

## Roadmap
- [ ] Room database (offline-first)
- [ ] Monthly summary (SQL aggregation)
- [ ] DataStore settings (theme, currency)
- [ ] Unit tests (domain)
- [ ] CI (GitHub Actions)

## Commit convention
This project follows Conventional Commits (e.g. `feat: ...`, `fix: ...`, `chore: ...`).

## Author
- Dang Tran Anh Quan (HCMC, Vietnam)
