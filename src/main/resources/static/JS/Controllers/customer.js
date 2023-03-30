const init = (async () => {
  await checkAuth();
  createLayout(getColumnInfo(), getSearch());
})()
let column;
const getColumnInfo = () => {
  let headers = [];

  // id
  column = new columnInfo();
  column.title = "Mã Khách Hàng";
  column.name = "id";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  column.hidden = true;
  headers.push(column);

  // customerName
  column = new columnInfo();
  column.title = "Họ Tên";
  column.name = "customerName";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  headers.push(column);

  // gender
  column = new columnInfo();
  column.title = "Giới Tính";
  column.name = "gender";
  column.dataType = "";
  column.align = "center";
  column.width = "50";
  headers.push(column);

  // birthDate
  column = new columnInfo();
  column.title = "Ngày Sinh";
  column.name = "birthDate";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  headers.push(column);

  // nationalId
  column = new columnInfo();
  column.title = "Căn Cước";
  column.name = "nationalId";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  headers.push(column);

  // roomId
  column = new columnInfo();
  column.title = "Phòng Đang Ở";
  column.name = "roomId";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  headers.push(column);

  // roomId
  column = new columnInfo();
  column.title = "Hành Động";
  column.name = "actions";
  column.dataType = "";
  column.template = "actions";
  column.align = "center";
  column.width = "";
  headers.push(column);

  return headers;
}

const getSearch = async () => {
  const request = getRequest();
  request['customerSearchConditions'] = {
    search: document.getElementById('search-input').value
  }
  try {
    const response = await callAPI('customerSearch', request);
    if(checkError(response.data)) return;
    console.log(response.data);
    return response.data.rows;
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}

