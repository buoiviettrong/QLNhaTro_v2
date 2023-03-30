const LogOut = async () => {
  const request = getRequest();
  const response = await callAPI('logout', request);
  if(checkError(response.data)) return;
  alert('Logged out successfully');
  localStorage.removeItem('loginInfo');
  navigateTo('login');
}