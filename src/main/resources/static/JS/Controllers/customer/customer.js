const init = (async () => {
   await checkAuth();
   await loadGrid(getRequest());
})()
let column;
const request = getRequest();
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

  // phoneNumber
  column = new columnInfo();
  column.title = "Số Liên Hệ";
  column.name = "phoneNumber";
  column.dataType = "";
  column.align = "right";
  column.width = "";
  headers.push(column);

  // roomId
  column = new columnInfo();
  column.title = "Mã Phòng Đang Ở";
  column.name = "roomId";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  column.hidden = true;
  headers.push(column);

  // roomName
  column = new columnInfo();
  column.title = "Phòng Đang Ở";
  column.name = "roomName";
  column.dataType = "";
  column.align = "center";
  column.width = "";
  headers.push(column);

  // actions
  column = new columnInfo();
  column.title = "Hành Động";
  column.name = "actions";
  column.dataType = "";
  column.template = `<button class="btn btn-primary ms-2 me-2" onclick="Detail('customerDetailModal', '{id}')">Chi tiết</button><button class="btn btn-danger ms-2 me-2" onclick="Delete('{id}')">Xóa</button>`;
  column.align = "center";
  column.width = "";
  headers.push(column);

  return headers;
}

const getSearch = async (request) => {
  request['customerSearchConditions'] = {
    search: document.getElementById('search-input').value
  }
  try {
    const response = await callAPI('customerSearch', request);
    if(checkError(response)) return;
    return response;
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}

const loadGrid = async (request) => {
  const headers = getColumnInfo();
  const response = await getSearch(request);
  if(response.rows.length === 0) alert("No search");
  createLayout(headers, response.rows);
  createPagination(response.pageInfo);
}

const Search = async () => {
  await loadGrid(getRequest());
}

const Delete = async (id) => {
  const request = getRequest();
  request['customerDeleteId'] = id;
  try {
    const response = await callAPI('customerDelete', request);
    console.log(response);
    if(checkError(response)) return;
    alert("Deleted successful");
    await loadGrid(getRequest());
  } catch (e) {
    alert("ERROR OUTSIDE SYSTEM" + e.message);
  }
}

const changePage = async (index) => {
  request.pageInfo.pageNum = index;
  await loadGrid(request);
}
