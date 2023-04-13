
const check_value = (getRequest_customer_create) => {
  const customerName_message = document.getElementById('customerName-message');
  const birthDate_message = document.getElementById('birthDate-message');
  const gender_message = document.getElementById('gender-message');
  const phoneNumber_message = document.getElementById('phoneNumber-message');
  const nationalId_message = document.getElementById('nationalId-message');
  customerName_message.innerText = '';
  birthDate_message.innerText = '';
  phoneNumber_message.innerText = '';
  nationalId_message.innerText = '';
  gender_message.innerText = '';

  let flg = true;
  if (getRequest_customer_create.customerName === '') {
    customerName_message.innerText = "Customer Name is required.";
    flg = false;
  }
  if (getRequest_customer_create.birthDate === '') {
    birthDate_message.innerText = "Birth Date is required.";
    flg = false;
  }
  if (getRequest_customer_create.gender === '') {
    gender_message.innerText = "Gender is required.";
    flg = false;
  }
  if (getRequest_customer_create.nationalId === '') {
    nationalId_message.innerText = "National Id is required.";
    flg = false;
  }
  if (getRequest_customer_create.phoneNumber === '') {
    phoneNumber_message.innerText = "Phone Number is required.";
    flg = false;
  }
  return flg;
}
const _getRequest = () => {
  const customerName = document.getElementById('customerName');
  const birthDate = document.getElementById('birthDate');
  const gender = document.getElementById('gender');
  const address = document.getElementById('address');
  const emailAddress = document.getElementById('emailAddress');
  const nationalId = document.getElementById('nationalId');
  const phoneNumber = document.getElementById('phoneNumber');

  return {
    customerName: customerName.value,
    birthDate: birthDate.value,
    gender: gender.value,
    address: address.value,
    emailAddress: emailAddress.value,
    nationalId: nationalId.value,
    phoneNumber: phoneNumber.value
  }
}
const CreateNewCustomer = async () => {
  const getRequest_customer_create = _getRequest();
  if(!check_value(getRequest_customer_create)) return;

  const request = getRequest();
  request['customerCreateDto'] = getRequest_customer_create;
  try {
    const response = await callAPI('customerCreate', request);
    if(checkError(response)) return;
    alert("Customer created successfully");
    ToggleModal('none');
    await loadGrid(getRequest());
  } catch (err) {
    alert("Error Outside System");
  }
}
