# QLNhaTro_v2

Bài Làm Hỗ Trợ Hoc Tập

- Ngôn ngữ sử dụng: Java
- Framwork: Spring Boot
- CSDL: no-sql MongoDB
- Cấu trúc file em nhặt nhạnh nên cx không biết phải kiểu MVC ko, mong mọi người thông cảm

### Code viết về việc quản lý nhà trọ, gồm các chức năng sau 
1. Đăng Nhập, Đăng Ký, Đăng Xuất
2. Quản Lý phòng: thêm phòng, xóa phòng, thêm khách vào phòng, sửa phòng( khách + thông tin cơ bản), trả phòng
3. Quản lý khách: thêm mới khách, xóa khách, sửa thông tin khách
4. Tính tiền phòng: chỉnh sửa thông tin phòng như: số điện nước, số tiền đặt cọc và xuất hóa đơn
5. Quản lý hóa đơn: xem thông tin hóa đơn, xác nhận hóa đơn (đã trả hay còn nợ)

### luồng hoạt động cơ bản

1. Thêm khách vào phòng: nhấn nút "Thêm khách hàng" ở phòng tương ứng, chọn khách hàng từ ô "Khách hàng", nhấn dấu "+"
   -> trạng thái phòng chuyển sang có người ở, ở bảng tính tiền đổi trạng thái + xuất hiện thêm nút xuất hóa đơn
2. Trả phòng: chuyển trạng thái phòng + trạng thái ở bảng tính tiền, xóa danh sách khách hàng ra khỏi phòng 
  
3. Tính tiền phòng: nhập dữ liệu dòng tương ứng sẽ tự cập nhật vào CSDL
4. Xuất hóa đơn: điều chỉnh lại chỉ số điện/nước (cũ = mới), tiền đặt cọc(giảm theo tổng tiền cần thu) -> tạo hóa đơn mới
5. Xác nhận hóa đơn: nhập tiền mà khách hàng trả -> nhấn "Xác Nhận" -> cập nhật lại số tiền cần thu còn lại, nếu = 0 chuyển trạng thái đơn thành "Đã Thu"
