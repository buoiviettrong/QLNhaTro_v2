const baseUrls = `http://localhost:5000`;

const callAPI = async (funcName, req) => {
  return await axios.post(`${baseUrls}/${funcName}`, req);;
}

const checkError = (res) => {
  if (res.errorList !== null && res.errorList.length > 0) {
    alert(res.errorList[0].errorMsg);
    return true;
  }
  return false;
}

const getRequest = () => {
  const { id, token } = getAccessInfo();
  return {
    accessInfo: {
      userId: id,
      token: token
    }
  }
}
const getAccessInfo = () => {
  const accessInfo = JSON.parse(localStorage.getItem("loginInfo"));
  return accessInfo;
}

const checkAuth = async () => {
  const request = getRequest();
  const response = await callAPI('Authorized', request);
  if(!response) {
    alert("You are not authorized to access this page.");
    window.location = `${baseUrls}/login`;
  }
}

const navigateTo = (to) => {
  window.location = `${baseUrls}/${to}`;
}