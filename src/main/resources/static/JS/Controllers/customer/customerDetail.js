let customerDetails = {
  id: '',
  customerName: '',
  birthDate: '',
  gender: 1,
  address: '',
  nationalId: '',
  phoneNumber: '',
  emailAddress: '',
  roomName: '',
  statusChangeNationalId: 0
}
const Detail = async (name, _id) => {
  await OpenModal(name);
  await loadDetails(_id);
}
const loadDetails = async (_id) => {
  const request = getRequest();
  request['customerSearchConditions'] = {
    'id': _id
  }
  customerDetails = {};
  try {
    const response = await callAPI('customerSearch', request);
    if(checkError(response)) return;
    inputDataToModal(response);
  } catch (err) {
    alert("ERROR OUTSIDE SYSTEM")
  }
}
const setDetails = () => {
  customerDetails.id = document.getElementById('id').value;
  customerDetails.customerName = document.getElementById('customerName').value;
  customerDetails.birthDate = document.getElementById('birthDate').value;
  customerDetails.gender = document.getElementById('gender').value;
  customerDetails.address = document.getElementById('address').value;
  customerDetails.nationalId = document.getElementById('nationalId').value;
  customerDetails.phoneNumber= document.getElementById('phoneNumber').value;
  customerDetails.emailAddress = document.getElementById('emailAddress').value;
  customerDetails.roomName = document.getElementById('roomName').value;
}
const inputDataToModal = (response) => {
  const data = response.rows[0];
  document.getElementById('id').value = data.id;
  document.getElementById('customerName').value = data.customerName;
  document.getElementById('birthDate').value = data.birthDate;
  document.getElementById('gender').value = data.gender;
  document.getElementById('address').value = data.address;
  document.getElementById('nationalId').value = data.nationalId;
  document.getElementById('phoneNumber').value = data.phoneNumber;
  document.getElementById('emailAddress').value = data.emailAddress;
  document.getElementById('roomName').value = data.roomName;
  setDetails();
}
const editCustomer = async () => {
  const request = getRequest();
  if(document.getElementById('nationalId').value !== customerDetails.nationalId) customerDetails.statusChangeNationalId = 1;
  setDetails();
  request['customerUpdateDto'] = customerDetails;
  try {
    const response = await callAPI('customerUpdate', request);
    if(checkError(response)) return;
    alert('Customer updated successfully');
    await loadGrid(getRequest());
  } catch (err) {
    alert("ERROR OUTSIDE SYSTEM")
  }
}