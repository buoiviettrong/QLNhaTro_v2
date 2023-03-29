const LogOut = async () => {
  const response = await callAPI('logout');
  if(checkError(response.data)) return;
  alert('Logged out successfully');
  localStorage.removeItem('loginInfo');
  navigateTo('login');
}