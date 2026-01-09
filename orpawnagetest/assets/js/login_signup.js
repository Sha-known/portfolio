/*=============== LOGIN/SIGNUP ANIMATION ===============*/
const container = document.querySelector('.container');
const registerBtn = document.querySelector('.register-btn');
const loginBtn = document.querySelector('.login-btn');
const formBox = document.querySelector('.form-box');

registerBtn.addEventListener('click', () => {
  container.classList.add('active');
  togglePasswordVisiblity();
  clearLoginData();
});

loginBtn.addEventListener('click', () => {
  container.classList.remove('active');
  togglePasswordVisiblity();
  clearSignUpData();
});

/*=============== TOGGLE PASSWORD VISIBILITY ===============*/
const togglePasswordVisiblity = () => {
  const input = container.classList.contains('active')
    ? 'regPasswordInput'
    : 'logPasswordInput';
  const toggleIcon = container.classList.contains('active')
    ? 'regToggleIcon'
    : 'logToggleIcon';

  const passwordInput = document.getElementById(input);
  const icon = document.getElementById(toggleIcon);

  document.getElementById(toggleIcon).addEventListener('click', function () {
    if (passwordInput.type === 'password') {
      passwordInput.type = 'text';
      icon.classList.remove('bxs-show');
      icon.classList.add('bxs-hide');
    } else {
      passwordInput.type = 'password';
      icon.classList.remove('bxs-hide');
      icon.classList.add('bxs-show');
    }
  });
};

/*=============== CLEAR INPUT VALUE ===============*/
const clearLoginData = () => {
  const loginInputs = formBox.querySelectorAll('.login form .input-box input');

  loginInputs.forEach((input) => {
    input.value = '';
  });
};

const clearSignUpData = () => {
  const registerInputs = document.querySelectorAll(
    '.register form .input-box input'
  );

  registerInputs.forEach((input) => {
    input.value = '';
  });
};

togglePasswordVisiblity();
