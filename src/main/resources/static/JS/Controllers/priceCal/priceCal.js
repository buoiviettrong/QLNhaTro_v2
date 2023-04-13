const init = (async () => {
  await checkAuth();
  await loadGrid(getRequest());
})()
let column;
const request = getRequest();
const getColumnInfo = () => {
  let headers = [];

  column = new columnInfo();
  column.title = "ID";
  column.name = "id";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = true;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tên Phòng";
  column.name = "roomName";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Trạng Thái";
  column.name = "Status";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ Số Điện Cũ";
  column.name = "electricIndexOld";
  column.dataType = "Input";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ Số Điện Mới";
  column.name = "electricIndexNew";
  column.dataType = "Input";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Giá Điện";
  column.name = "priceOfElectric";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng Tiền Điện";
  column.name = "totalMoneyOfElectric";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ Số Nước Cũ";
  column.name = "waterIndexOld";
  column.dataType = "Input";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ Số Nươc Mới";
  column.name = "waterIndexNew";
  column.dataType = "Input";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Giá Nước";
  column.name = "priceOfWater";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng Tiền Nước";
  column.name = "totalMoneyOfWater";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Giá Phòng";
  column.name = "roomPrice";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tiền Cọc";
  column.name = "deposit";
  column.dataType = "Input";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng Tiền";
  column.name = "totalMoney";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Thực Thu";
  column.name = "totalRevenue";
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Hành Động";
  column.name = "action";
  column.template = `<button class="btn btn-primary" onclick="Output('{id}')" id="{id}">Xuất Đơn</button>`;
  column.dataType = "Text";
  column.width = 100;
  column.align = "right"; // left.center.right
  column.hidden = false;
  headers.push(column);

  return headers;
}
const getPriceCalSearchConditions = () => {
  return {
    roomName: document.getElementById("roomName").value,
    roomStatus: document.getElementById("roomStatus").value
  }
}
const getSearch = async (request) => {
  request['priceCalSearchConditions'] = getPriceCalSearchConditions();
  try {
    const response = await callAPI('priceCalSearch', request);
    if(checkError(response)) return;
    return response;
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}
const converts = (arr) => {
  const result = [];
  arr.forEach(item => {
    const value = {
      id: item.id,
      roomName: item.roomName,
      Status: item.Status === 1 ? "Đang Ở" : "Trống",
      electricIndexOld: item['electricIndex'].Old,
      electricIndexNew: item['electricIndex'].New,
      priceOfElectric: item['Price'][0].priceOfElectric,
      totalMoneyOfElectric: item.totalMoneyOfElectric,
      waterIndexOld: item['waterIndex'].Old,
      waterIndexNew: item['waterIndex'].New,
      priceOfWater: item['Price'][0].priceOfWater,
      totalMoneyOfWater: item.totalMoneyOfWater,
      deposit: item.deposit,
      roomPrice: item.roomPrice,
      totalMoney: item.totalMoney,
      totalRevenue: item.totalRevenue
    }
    result.push(value);
  })
  return result;
}
const loadGrid = async (request) => {
  const headers = getColumnInfo();
  const response = await getSearch(request);
  const new_row = converts(response.rows);
  if(response.rows.length === 0) alert("No search");
  createLayout(headers, new_row);
  createPagination(response.pageInfo);
  response.rows.forEach(item => {
    const btn = document.getElementById(`${item.id}`);
    btn.style.display = item.Status === 1 ? "block" : "none";
  })
}

const Search = async () => {
  await loadGrid(getRequest());
}

const getPriceCalUpdateDto = (id) => {
  return {
    id: id,
    Status: -1,
    electricIndex : {
      Old: document.getElementById(`electricIndexOld_${id}`).value,
      New: document.getElementById(`electricIndexNew_${id}`).value
    },
    waterIndex: {
      Old: document.getElementById(`waterIndexOld_${id}`).value,
      New: document.getElementById(`waterIndexNew_${id}`).value
    },
    deposit: document.getElementById(`deposit_${id}`).value
  }
}
const checkValue = (request) => {
  try {
    const _request = {
      electricIndex: {
        Old: parseInt(`${request.electricIndex.Old}`),
        New: parseInt(`${request.electricIndex.New}`)
      },
      waterIndex: {
        Old: parseInt(`${request.waterIndex.Old}`),
        New: parseInt(`${request.waterIndex.New}`)
      },
      deposit: parseInt(`${request.deposit}`)
    }
    if (isNaN(_request.electricIndex.Old)) {
      alert("Chỉ Số Điện Cũ Nhập Vào Phải Là Số");
      return false;
    }
    if (isNaN(_request.electricIndex.New)) {
      alert("Chỉ Số Điện Mới Nhập Vào Phải Là Số");
      return false;
    }
    if (isNaN(_request.waterIndex.Old)) {
      alert("Chỉ Số Nước Cũ Nhập Vào Phải Là Số");
      return false;
    }
    if (isNaN(_request.waterIndex.New)) {
      alert("Chỉ Số Nước Mới Nhập Vào Phải Là Số");
      return false;
    }
    if (isNaN(_request.deposit)) {
      alert("Tiền Cọc Nhập Vào Phải Là Số");
      return false;
    }
    if (_request.electricIndex.New < _request.electricIndex.Old) {
      alert("Chỉ Số Điện Mới Nhập Vào Phải Lớn Hơn Chỉ Số Điện Cũ");
      return false;
    }
    if (_request.waterIndex.New < _request.waterIndex.Old) {
      alert("Chỉ Số Nước Mới Nhập Vào Phải Lớn Hơn Chỉ Số Điện Cũ");
      return false;
    }
    if (_request.deposit < 0
      || _request.electricIndex.New < 0
      || _request.waterIndex.New < 0
      || _request.electricIndex.Old < 0
      || _request.waterIndex.Old < 0) {
      alert("Số Nhập Vào Phải Là Số Dương");
      return false;
    }
  }catch (err) {
    alert("Số Nhập Vào Phải Là Số");
  }
  return true;
}
const Change = async (id) => {
  const priceCalUpdateDto = getPriceCalUpdateDto(id);
  if(!checkValue(priceCalUpdateDto)) {
    await loadGrid(request);
    return;
  }
  request['priceCalUpdateDto'] = priceCalUpdateDto;
  try {
    const response = await callAPI("priceCalUpdate", request);
    if(checkError(response)) return;
    await loadGrid(request);
    alert("Cập Nhật Dữ Liêu Thành Công");
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}

const Output = async (id) => {
  let request = getRequest();
  request['id'] = id;
  try {
    let response = await callAPI("priceCalculation", request);
    if(checkError(response)) return;
    // tạo hóa đơn
    request = getRequest();
    request['receiptCreateDto'] = response.rows;
    response = await callAPI("receiptCreate", request);
    if(checkError(response)) return;
    alert("Tạo Hóa Đơn Mới Thành Công");
    await loadGrid(getRequest());
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}
const changePage = async (index) => {
  request.pageInfo.pageNum = index;
  await loadGrid(request);
}