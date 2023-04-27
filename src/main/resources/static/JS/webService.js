const baseUrls = window.location.hostname;

const callAPI = async (funcName, req) => {
  const response = await fetch(`${baseUrls}/${funcName}`, {
    method: 'POST',
    headers: {
      'content-type': 'application/json',
      'charset': 'charset=utf-8',
    },
    body: JSON.stringify(req)
  })
  return response.json();
}

const checkError = (res) => {
  if (res.errorList !== null && res.errorList.length > 0) {
    alert(res.errorList[0].errorMsg);
    return true;
  }
  return false;
}

const getRequest = () => {
  if(localStorage.getItem("loginInfo") !== null && localStorage.getItem("loginInfo") !== undefined) {
    const { id, token } = getAccessInfo();
    return {
      accessInfo: {
        userId: id,
        token: token
      },
      pageInfo: {
        pageNum: 1,
        displayNum: 8
      }
    }
  }
}
const getAccessInfo = () => {
  return JSON.parse(localStorage.getItem("loginInfo"));
}

const checkAuth = async () => {
  const request = getRequest();
  if(request === undefined) {
    alert("You are not authorized to access this page.");
    window.location = `${baseUrls}/login`;
  }
  const response = await callAPI('Authorized', request);
  if(!response) {
    alert("You are not authorized to access this page.");
    window.location = `${baseUrls}/login`;
  }
}

const navigateTo = (to) => {
  window.location = `${baseUrls}/${to}`;
}

const GetModal = (name) => {
  const modal = fetch(`modal/${name}`);
  return modal
          .then(response => response.text())
          .then(data => data);
}
