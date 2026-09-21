# BÁO CÁO THỰC HÀNH 1: TRIỂN KHAI FALLBACK PATTERN BẢO VỆ GIAO DIỆN TRANG CHỦ

## 1. Mục tiêu
- Nắm vững cú pháp khai báo `@CircuitBreaker` của thư viện Resilience4j trong Spring Boot.
- Thiết kế chuẩn chữ ký hàm Fallback để thực hiện cơ chế *Graceful Degradation* (thoái lui mềm dẻo).
- Bảo vệ StoreX-BFF không bị lỗi sập trang (HTTP 500) khi dịch vụ phụ thuộc (Marketing-Service) gặp sự cố hoặc đang bảo trì.

3. Kết quả Thực nghiệm & Kiểm thử3.1. Gọi API Trang chủ khi Marketing-Service bị gián đoạnEndpoint: GET http://localhost:8080/api/v1/home/vouchersHTTP Status: 200 OK (thay vì văng lỗi 500 Internal Server Error).Dữ liệu JSON nhận được:JSON[
   {
   "code": "DEFAULT_FREESHIP",
   "title": "Mã Freeship 15K",
   "discountAmount": 15000
   }
   ]
   3.2. Nhật ký hệ thống (Console Logs)PlaintextINFO  c.e.m.service.VoucherService : Dang goi sang Marketing-Service: http://localhost:9999/api/v1/marketing/flash-vouchers
   WARN  c.e.m.service.VoucherService : Marketing-Service gap su co: I/O error on GET request for "http://localhost:9999/api/v1/marketing/flash-vouchers": Connection refused. Kich hoat Fallback tra ve voucher mac dinh.
4. Checklist Tự Đánh GiáTiêu chí đánh giáTrạng tháiGhi chú kiểm traKiểu trả về của hàm FallbackĐạtTrả về đúng List<VoucherResponse> tương thích với hàm gốc.Danh sách tham số của hàm FallbackĐạtTham số cuối cùng bắt buộc là Throwable throwable.Bảo vệ hệ thống (Graceful Degradation)ĐạtTrả về mã voucher DEFAULT_FREESHIP với HTTP 200 OK dù hệ thống nguồn bị sập.