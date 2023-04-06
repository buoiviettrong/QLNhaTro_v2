const init = (async () => {
  await checkAuth();
  await loadGrid();
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
  column.title = "Tổng Thu (Vnđ)";
  column.name = "totalRevenue";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  // column = new columnInfo();
  // column.title = "Hành Động";
  // column.name = "action";
  // column.template = `<buton class="btn btn-primary" onclick="Detail('receiptDetailModal', '{id}')" data-bs-toggle="modal" data-bs-target="#myModal">Chi Tiết</buton>`;
  // column.dataType = "Text";
  // column.width = 100;
  // column.align = "center"; // left.center.right
  // column.hidden = false;
  // headers.push(column);

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
      timestamp: item.timestamp
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
    search: document.getElementById('search-input').value
  }
}
const getSearch = async () => {
  const request = getRequest();
  request['receiptSearchConditions'] = getReceiptSearchConditions();
  try {
    const response = await callAPI('receiptSearch', request);
    if(checkError(response)) return;
    return response.rows;
  } catch (err) {
    alert("Lỗi Ngoài Hệ Thống " + err.message);
  }
}

const loadGrid = async () => {
  const headers = getColumnInfo();
  const rows = await getSearch();
  const new_rows = converts(rows);
  if(rows.length === 0) alert("No search");
  createLayout(headers, new_rows);
}

const Search = async () => {
  await loadGrid();
}

const Clear = () => {
  document.getElementById('startPrice').value = 0;
  document.getElementById('endPrice').value = 0;
  document.getElementById('startDate').value = '';
  document.getElementById('endDate').value = '';
  document.getElementById('search-input').value = '';
}

const Detail = (id) => {

}