const init = (async () => {
  await checkAuth();
  await loadGrid(getRequest());
})()
let column;
const getColumnInfo = () => {
  const headers = [];

  let column = new columnInfo();
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
  column.template = "";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Thời Gian Xuất";
  column.name = "timestamp";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số nước cũ";
  column.name = "waterIndexOld";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số nước mới";
  column.name = "waterIndexNew";
  column.dataType = "Text";
  column.width = 150;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng tiền nước";
  column.name = "totalMoneyOfWater";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số điện cũ";
  column.name = "electricIndexOld";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số điện mới";
  column.name = "electricIndexNew";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng tiền điện";
  column.name = "totalMoneyOfElectric";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tiền cọc";
  column.name = "deposit";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tiền Phòng";
  column.name = "roomPrice";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng Phải Thu (Vnđ)";
  column.name = "totalRevenue";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tiền Thu Còn Lại (Vnđ)";
  column.name = "remainingMoney";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tiền Trả (Vnđ)";
  column.name = "amountPaid";
  column.dataType = "Input";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Hành Động";
  column.name = "action";
  column.template = `<buton class="btn btn-primary" onclick="Confirm('{id}')" id="btn_{id}">Xác Nhận</buton>`;
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  return headers;
}
const converts = (arr) => {
  const result = [];
  arr.forEach(item => {
    const value = {
      id: item.id,
      roomName: item.roomName,
      electricIndexOld: item['electricIndex'].Old,
      electricIndexNew: item['electricIndex'].New,
      priceOfElectric: item['Price'][0].priceOfElectric,
      totalMoneyOfElectric: item['Price'][0].priceOfElectric * (item['electricIndex'].New - item['electricIndex'].Old),
      waterIndexOld: item['waterIndex'].Old,
      waterIndexNew: item['waterIndex'].New,
      priceOfWater: item['Price'][0].priceOfWater,
      totalMoneyOfWater: item['Price'][0].priceOfWater * (item['waterIndex'].New - item['waterIndex'].Old),
      deposit: item.deposit,
      roomPrice: item.roomPrice,
      totalMoney: item.totalMoney,
      totalRevenue: item.totalRevenue,
      timestamp: item.timestamp,
      remainingMoney: item.remainingMoney,
      amountPaid: item.amountPaid
    }
    result.push(value);
  })
  return result;
}
const getReceiptSearchConditions = () => {
  return {
    price: {
      start: document.getElementById('startPrice').value,
      end: document.getElementById('endPrice').value
    },
    date: {
      start: document.getElementById('startDate').value,
      end: document.getElementById('endDate').value
    },
    receiptStatus : document.getElementById("receiptStatus").value,
    search: document.getElementById('search-input').value
  }
}
const getSearch = async (request) => {
  request['receiptSearchConditions'] = getReceiptSearchConditions();
  try {
    const response = await callAPI('receiptSearch', request);
    if(checkError(response)) return;
    return response;
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}

const loadGrid = async (request) => {
  const headers = getColumnInfo();
  const response =  await getSearch(request);
  console.log(response);
  const rows = response.rows;
  const new_rows = converts(rows);
  if(rows.length === 0) alert("No search");
  createLayout(headers, new_rows);
  createPagination(response.pageInfo);

  clearButton(new_rows);
}
const clearButton = (rows) => {
  rows.forEach(row => {
    if(row.remainingMoney === 0) {
      const HTMLElement = document.getElementById(`btn_${row.id}`);
      console.log(HTMLElement, row.id);
      HTMLElement.classList.replace('btn-primary', 'btn-success');
      HTMLElement.attributes.removeNamedItem("onclick");
      HTMLElement.innerText = 'Đã Trả';
      console.log("-----", HTMLElement);
    }
  })
}
const Search = async () => {
  await loadGrid(getRequest());
}

const Clear = () => {
  document.getElementById('startPrice').value = 0;
  document.getElementById('endPrice').value = 0;
  document.getElementById('startDate').value = '';
  document.getElementById('endDate').value = '';
  document.getElementById('search-input').value = '';
  document.getElementById('receiptStatus').value = -1;
}

const Detail = (id) => {

}
const request = getRequest();
const changePage = async (index) => {
  request.pageInfo.pageNum = index;
  await loadGrid(request);
}

const Confirm = async (id) => {
  const request = getRequest();
  request['receiptConfirmDto'] = {
    id: id,
    amountPaid: +document.getElementById(`amountPaid_${id}`).value
  }
  try {
    const response = await callAPI('receiptConfirm', request);
    if(checkError(response)) return;
    await loadGrid(getRequest());
    alert("Sửa Thông Tin Thành Công");
  } catch (e) {
    alert("Error: " + e.message);
  }
}