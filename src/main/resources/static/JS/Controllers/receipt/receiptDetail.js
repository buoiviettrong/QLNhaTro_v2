const Detail = async (name, id) => {
  await OpenModal(name);
  await loadDetails(id);
}

const loadDetails = async (id) => {
  const request = getRequest();
  request['receiptSearchConditions'] = {
    id: id
  }
  try {
    const response = await callAPI("receiptSearch", request);
    if(checkError(response)) return;
    addData(response.rows[0]);
  } catch (err) {
    alert("ERROR OUTSIDE SYSTEM" + err.message);
  }
}
const addData = () => {

}