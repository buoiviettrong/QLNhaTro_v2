const baseUrls = `http://localhost:5000`;

const callAPI = async (funcName, req) => {
  const response = await axios.post(`${baseUrls}/${funcName}`, req);
  return response.data;
}

const checkErrorList = (errorList) => {
  if(errorList != null && errorList.length > 0) {
    alert(errorList[0].errorMsg);
    return;
  }
}
