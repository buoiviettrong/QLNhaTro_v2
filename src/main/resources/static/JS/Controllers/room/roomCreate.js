const roomCreateDto = () => {
  return {
    roomName: document.getElementById('_roomName').value,
    price: document.getElementById('_price').value
  }
}
const checkValue = () => {
  const roomCreate = roomCreateDto();
  const roomName = document.getElementById('roomName-message');
  const price = document.getElementById('price-message');
  roomName.innerText = '';
  price.innerText = '';

  let flg = true;
  if(roomCreate.roomName === '') {
    roomName.innerText = 'Room name is required';
    flg = false;
  }
  if (roomCreate.price === '') {
    price.innerText = 'Price is required';
    flg = false;
  }
  if(Number.isNaN(roomCreate.price)) {
    price.innerText = 'Price must be an integer';
    flg = false;
  }
  return flg;
}
const createRoom = async () => {
  let request = getRequest();
  if(!checkValue()) return;
  request['roomCreateDto'] = roomCreateDto();
  try {
    let response = await callAPI('roomCreate', request);
    if(checkError(response)) return;
    request = getRequest();
    request['priceCalCreateDto'] = {
      roomName: roomCreateDto().roomName,
      roomPrice: roomCreateDto().price,
      roomId: response.id
    }
    response = await callAPI('priceCalCreate', request);
    if(checkError(response)) return;
    alert("Created room successfully");
    await loadRoom(getRequest());
  } catch (e) {
    alert("ERROR OUTSIDE SYSTEM" + e.message())
  }
}