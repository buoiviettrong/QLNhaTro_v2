const getRequest = () => {
  return {
    accessInfo: {
      userId: '9999',
      token: '9999'
    }
  }
}
const CreateNewUser = async () => {
  const request = getRequest();
  request['userCreateDto'] = {
    username: document.getElementById('username').value,
    email: document.getElementById('email').value,
    password: document.getElementById('password').value,
    firstName: document.getElementById('firstName').value,
    lastName: document.getElementById('lastName').value,
    phoneNumber: document.getElementById('phoneNumber').value,
    address: document.getElementById('address').value,
    gender: document.getElementById('gender').value,
    birthDate: document.getElementById('birthDate').value,
    description: document.getElementById('description').value
  };

  try {
    const response = await callAPI('userCreate', request);

    if(response.errorList !== undefined && response.errorList.length > 0) {
      alert(response.errorList[0].errorMsg);
      return;
    }

    console.log(response.rows);

  } catch (err) {
    alert(err.message);
  }
}

const Search = async () => {
  const request = getRequest();
  request['userSearchConditions'] = {
    name: document.getElementById('search-inp').value,
    status: -1
  }
  try {
    const response = await callAPI('userSearch', request);

    if(response.errorList !== undefined && response.errorList.length > 0) {
      alert(response.errorList[0].errorMsg);
      return;
    }

    console.log(response.rows);

  } catch (err) {
    alert(err.message);
  }

}