const items = document.getElementById('content-body');
const init = (async () => {
  await checkAuth();
  const rows = await getRows();
  loadRoom(rows);
})()
let lists = [];
const roomNoActive = (id, name, price) => {
  return `<div class="room-item">
            <div class="room-item-header">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                <path d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/>
              </svg>
              <span class="ms-3 fs-3 fw-bold">${name}</span>
            </div>
            <div class="room-item-description">
              <div class="btn-customer">
                <button class="btn btn-success" onclick="AddCustomer('roomAddCustomer', '${id}')">Thêm Khách Hàng</button>
              </div>
              <div class="room-price">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                  <path d="M320 96H192L144.6 24.9C137.5 14.2 145.1 0 157.9 0H354.1c12.8 0 20.4 14.2 13.3 24.9L320 96zM192 128H320c3.8 2.5 8.1 5.3 13 8.4C389.7 172.7 512 250.9 512 416c0 53-43 96-96 96H96c-53 0-96-43-96-96C0 250.9 122.3 172.7 179 136.4l0 0 0 0c4.8-3.1 9.2-5.9 13-8.4zm84 88c0-11-9-20-20-20s-20 9-20 20v14c-7.6 1.7-15.2 4.4-22.2 8.5c-13.9 8.3-25.9 22.8-25.8 43.9c.1 20.3 12 33.1 24.7 40.7c11 6.6 24.7 10.8 35.6 14l1.7 .5c12.6 3.8 21.8 6.8 28 10.7c5.1 3.2 5.8 5.4 5.9 8.2c.1 5-1.8 8-5.9 10.5c-5 3.1-12.9 5-21.4 4.7c-11.1-.4-21.5-3.9-35.1-8.5c-2.3-.8-4.7-1.6-7.2-2.4c-10.5-3.5-21.8 2.2-25.3 12.6s2.2 21.8 12.6 25.3c1.9 .6 4 1.3 6.1 2.1l0 0 0 0c8.3 2.9 17.9 6.2 28.2 8.4V424c0 11 9 20 20 20s20-9 20-20V410.2c8-1.7 16-4.5 23.2-9c14.3-8.9 25.1-24.1 24.8-45c-.3-20.3-11.7-33.4-24.6-41.6c-11.5-7.2-25.9-11.6-37.1-15l0 0-.7-.2c-12.8-3.9-21.9-6.7-28.3-10.5c-5.2-3.1-5.3-4.9-5.3-6.7c0-3.7 1.4-6.5 6.2-9.3c5.4-3.2 13.6-5.1 21.5-5c9.6 .1 20.2 2.2 31.2 5.2c10.7 2.8 21.6-3.5 24.5-14.2s-3.5-21.6-14.2-24.5c-6.5-1.7-13.7-3.4-21.1-4.7V216z"/>
                </svg>
                <span class="ms-5 fs-5 fw-bold">${price} đồng</span>
              </div>
            </div>
            <div class="room-item-footer">
              <div class="btn btn-primary btn-detail" onclick="Detail('${id}')">Chi Tiết</div>
              <div class="btn btn-danger" onclick="Delete('${id}')">Xóa</div>
            </div>
          </div>`;
}

const roomActive = (id, name, price) => {
  return `<div class="room-item active">
            <div class="room-item-header">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512">
                <path d="M575.8 255.5c0 18-15 32.1-32 32.1h-32l.7 160.2c0 2.7-.2 5.4-.5 8.1V472c0 22.1-17.9 40-40 40H456c-1.1 0-2.2 0-3.3-.1c-1.4 .1-2.8 .1-4.2 .1H416 392c-22.1 0-40-17.9-40-40V448 384c0-17.7-14.3-32-32-32H256c-17.7 0-32 14.3-32 32v64 24c0 22.1-17.9 40-40 40H160 128.1c-1.5 0-3-.1-4.5-.2c-1.2 .1-2.4 .2-3.6 .2H104c-22.1 0-40-17.9-40-40V360c0-.9 0-1.9 .1-2.8V287.6H32c-18 0-32-14-32-32.1c0-9 3-17 10-24L266.4 8c7-7 15-8 22-8s15 2 21 7L564.8 231.5c8 7 12 15 11 24z"/>
              </svg>
              <span class="ms-3 fs-3 fw-bold">${name}</span>
            </div>
            <div class="room-item-description">
              <div class="btn-customer">  
                <button class="btn btn-danger" onclick="Checkout('${id}')">Trả Phòng</button>
              </div>
              <div class="room-price">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                  <path d="M320 96H192L144.6 24.9C137.5 14.2 145.1 0 157.9 0H354.1c12.8 0 20.4 14.2 13.3 24.9L320 96zM192 128H320c3.8 2.5 8.1 5.3 13 8.4C389.7 172.7 512 250.9 512 416c0 53-43 96-96 96H96c-53 0-96-43-96-96C0 250.9 122.3 172.7 179 136.4l0 0 0 0c4.8-3.1 9.2-5.9 13-8.4zm84 88c0-11-9-20-20-20s-20 9-20 20v14c-7.6 1.7-15.2 4.4-22.2 8.5c-13.9 8.3-25.9 22.8-25.8 43.9c.1 20.3 12 33.1 24.7 40.7c11 6.6 24.7 10.8 35.6 14l1.7 .5c12.6 3.8 21.8 6.8 28 10.7c5.1 3.2 5.8 5.4 5.9 8.2c.1 5-1.8 8-5.9 10.5c-5 3.1-12.9 5-21.4 4.7c-11.1-.4-21.5-3.9-35.1-8.5c-2.3-.8-4.7-1.6-7.2-2.4c-10.5-3.5-21.8 2.2-25.3 12.6s2.2 21.8 12.6 25.3c1.9 .6 4 1.3 6.1 2.1l0 0 0 0c8.3 2.9 17.9 6.2 28.2 8.4V424c0 11 9 20 20 20s20-9 20-20V410.2c8-1.7 16-4.5 23.2-9c14.3-8.9 25.1-24.1 24.8-45c-.3-20.3-11.7-33.4-24.6-41.6c-11.5-7.2-25.9-11.6-37.1-15l0 0-.7-.2c-12.8-3.9-21.9-6.7-28.3-10.5c-5.2-3.1-5.3-4.9-5.3-6.7c0-3.7 1.4-6.5 6.2-9.3c5.4-3.2 13.6-5.1 21.5-5c9.6 .1 20.2 2.2 31.2 5.2c10.7 2.8 21.6-3.5 24.5-14.2s-3.5-21.6-14.2-24.5c-6.5-1.7-13.7-3.4-21.1-4.7V216z"/>
                </svg>
                <span class="ms-5 fs-5 fw-bold">${price} đồng</span>
              </div>
            </div>
            <div class="room-item-footer">
              <div class="btn btn-light btn-detail" onclick="Detail('${id}')">Chi Tiết</div>
              <div class="btn btn-danger" onclick="Delete('${id}')">Xóa</div>
            </div>
          </div>`;
}
const Detail = (id) => {
}
const Delete = (id) => {

}
const AddCustomer = async (name, id) => {
  await OpenModal(name);
  await loadData(id);
}

const loadData = async (id) => {
  let request = getRequest();
  request['roomSearchConditions'] = {
    id: id
  };
  try {
    let response = await callAPI("roomSearch", request);
    if(checkError(response)) return;
    document.getElementById('a_id').value = id;
    document.getElementById('a_roomName').value = response.rows[0].roomName;

    request = getRequest();
    request['customerSearchConditions'] = {
      search: ''
    }
    response = await callAPI("customerSearch", request);
    if(checkError(response)) return;
    addCustomerToSelect(response.rows);
  } catch (e) {
    alert("ERROR OUTSIDE SYSTEM \n" + e.message);
  }
}
const addCustomerToSelect = (rows) => {
  lists = [];
  document.getElementById('lists').innerHTML = '';
  const lst = document.getElementById('datalistOptions');
  lst.innerHTML = '';
  rows.forEach(item => {
    lst.innerHTML += `<option value="${item.customerName}" name="${item.id}" id="${item.customerName}">${item.nationalId}</option>`
  })
}
const addToList = () => {
  const name = document.getElementById('a_customers').value;
  const text = document.getElementById(name).innerText;
  const id = document.getElementById(name).getAttribute('name');
  const temp = lists.filter(item => item.n_id === text);
  if(temp != null && temp.length > 0){}
  else lists.push({ name: name, n_id: text, id:  id});
  let str = '';
  lists.forEach(item => {
     str += `<div class="m-3" id="__${item.n_id}">${item.name} || ${item.n_id}</div>`;
  })
  document.getElementById('lists').innerHTML = str;
}
const SaveCustomer = async () => {
  console.log(lists);
  const id = document.getElementById('a_id').value;
  const request = getRequest();
  request['roomUpdateDto'] = {
    id: id,
    customers: lists.map(item => item.id),
    status: -1
  }
  try {
    const response = await callAPI('roomUpdate', request);
    if(checkError(response)) return;
    alert("Updated customer successfully");
  } catch (e) {
    alert("ERROR OUTSIDE SYSTEM \n" + e.message);
  }
}
const getConditions = () => {
  return {
    roomName: document.getElementById('roomName').value,
    status: document.getElementById('room-status').value
  }
}
const getRows = async () => {
  const request = getRequest();
  request['roomSearchConditions'] = getConditions();
  try {
    const response = await callAPI('roomSearch', request);
    if(checkError(response)) return;
    return response.rows;
  } catch (e) {
    alert("ERROR OUTSIDE SYSTEM \n" + e.message);
  }
}
const loadRoom = (rows) => {
  let str = '';
  rows.forEach(row => {
    if(row.status == 0) str += roomNoActive(row.id, row.roomName, row.price);
    else str += roomActive(row.id, row.roomName, row.price);
  })
  items.innerHTML = str == null ? '' : str;
}
const Search = async () => {
  const rows = await getRows();
  if(rows.length === 0) alert("No rows found for search");
  loadRoom(rows);
}
