const check = (request) => {
  const username = request.loginInfo.username;
  const password = request.loginInfo.password;
  const msg_username = document.getElementById('username-feedback');
  const msg_password = document.getElementById('password-feedback');
  msg_username.style.display = 'none';
  msg_password.style.display = 'none';

  let ck = true;
  if(username === null || username === '' || username === undefined) {
    msg_username.innerText = 'Please enter a username';
    msg_username.style.display = 'block';
    ck = false;
  }
  if(password === null || password === '' || password === undefined) {
    msg_password.innerText = 'Please enter a password';
    msg_password.style.display = 'block';
    ck = false;
  }

  return ck;
}

const getRequest_login = () => {
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;
  return {
    loginInfo: {
      username: username,
      password: password
    }
  }
}

const Login = async () => {
  const request = getRequest_login();
  if(!check(request)) return;

  const response = await callAPI('Login', request);
  if (checkError(response)) return;

  alert("Login successful !!!");
  localStorage.setItem('loginInfo', JSON.stringify(response.loginResult));
  navigateTo('home');
}