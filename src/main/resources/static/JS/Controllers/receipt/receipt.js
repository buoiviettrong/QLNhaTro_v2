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
  column.name = "name";
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
  column.name = "w_old";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số nước mới";
  column.name = "w_new";
  column.dataType = "Text";
  column.width = 150;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng tiền nước";
  column.name = "w_total";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số điện cũ";
  column.name = "e_old";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Chỉ số điện mới";
  column.name = "e_new";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng tiền điện";
  column.name = "e_total";
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
  column.name = "r_price";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Tổng Thu (Vnđ)";
  column.name = "total";
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  column = new columnInfo();
  column.title = "Hành Động";
  column.name = "action";
  column.template = `<buton class="btn btn-primary" onclick="Detail('{id}')" data-bs-toggle="modal" data-bs-target="#myModal">Chi Tiết</buton>`;
  column.dataType = "Text";
  column.width = 100;
  column.align = "center"; // left.center.right
  column.hidden = false;
  headers.push(column);

  return headers;
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
  if(rows.length === 0) alert("No search");
  createLayout(headers, rows);
}

const Search = async () => {
  await loadGrid();
}
