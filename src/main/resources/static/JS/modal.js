const modal = document.getElementById('modal');
const OpenModal = async (name) => {
  modal.innerHTML = '';
  const body = await GetModal(name);
  const modal_background = document.createElement('div');
  modal_background.classList.add('modal-background');
  modal_background.id = 'modal-background';

  modal.appendChild(modal_background);
  modal.innerHTML += body;
  ToggleModal("block");

  const btn_close_modal = document.getElementById('btn-close-modal');
  const modal_close = document.getElementById('modal-close');
  btn_close_modal.addEventListener('click', () => ToggleModal("none"));
  modal_close.addEventListener('click', () => ToggleModal("none"));
}
const ToggleModal = (display) => {
  modal.style.display = display;
}
