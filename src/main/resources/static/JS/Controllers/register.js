
const getRequest_register = () => {
  const username = document.getElementById('username');
  const password = document.getElementById('password');
  const confirm_password = document.getElementById('confirm-password');
  const email = document.getElementById('email');
  const phone = document.getElementById('phone');

  return {
    registerDto: {
      username: username.value,
      password: password.value,
      confirm_password: confirm_password.value,
      email: email.value,
      phone: phone.value
    }
  }
}

const clearMessage = () => {
  const h_username = document.getElementById('h-username');
  const h_password = document.getElementById('h-password');
  const h_confirm_password = document.getElementById('h-confirm-password');
  const h_email = document.getElementById('h-email');
  const h_phone = document.getElementById('h-phone');

  h_username.style.display = 'none';
  h_password.style.display = 'none';
  h_confirm_password.style.display = 'none';
  h_email.style.display = 'none';
  h_phone.style.display = 'none';

  h_username.innerText = '';
  h_password.innerText = '';
  h_confirm_password.innerText = '';
  h_email.innerText = '';
  h_phone.innerText = '';

  return {h_username, h_password, h_confirm_password, h_email, h_phone};
}

const checkValues = (request) => {
  const {h_username, h_password, h_confirm_password, h_email, h_phone} = clearMessage();
  let flg = true;

  if (request.registerDto.username === '') {
    h_username.style.display = 'block';
    h_username.innerText = "Username is required.";
    flg = false;
  }
  if (request.registerDto.password === '') {
    h_password.style.display = 'block';
    h_password.innerText = "Password is required.";
    flg = false;
  }
  if (request.registerDto.confirm_password === '') {
    h_confirm_password.style.display = 'block';
    h_confirm_password.innerText = "Confirm Password is required.";
    flg = false;
  }
  if (request.registerDto.email === '') {
    h_email.style.display = 'block';
    h_email.innerText = "Email is required.";
    flg = false;
  }
  if (request.registerDto.phone === '') {
    h_phone.style.display = 'block';
    h_phone.innerText = "Phone is required.";
    flg = false;
  }
  if(request.registerDto.password != request.registerDto.confirm_password) {
    h_confirm_password.style.display = 'block';
    h_confirm_password.innerText = "Password is not matching with Confirm Password";
    flg = false;
  }

  return flg;
}

const Register = async () => {
  const request = getRequest_register();
  const result = checkValues(request);
  if(result) {
    const response = await callAPI('register', request);
    if(checkError(response)) return;
    alert("Đăng ký thành công !!!");
    navigateTo('login');
  }
}