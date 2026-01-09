/*=============== SHOW MENU ===============*/
const navMenu = document.getElementById('nav-menu'),
  navToggle = document.getElementById('nav-toggle'),
  navClose = document.getElementById('nav-close');

/* Menu show */
if (navToggle) {
  navToggle.addEventListener('click', () => {
    navMenu.classList.add('show-menu');
  });
}

/* Menu hidden */
if (navClose) {
  navClose.addEventListener('click', () => {
    navMenu.classList.remove('show-menu');
  });
}

/*=============== USER PROFILE DROPDOWN MENU ===============*/

document.addEventListener('DOMContentLoaded', function () {
  const userSettings = document.getElementById('user_settings');
  const dropdownMenu = document.getElementById('dropdownMenu');

  userSettings.addEventListener('click', function (e) {
    e.preventDefault(); // Prevent default link behavior
    dropdownMenu.classList.toggle('active');
  });

  // Close the dropdown if clicked outside
  document.addEventListener('click', function (e) {
    if (!userSettings.contains(e.target)) {
      dropdownMenu.classList.remove('active');
    }
  });
});

/*=============== HOME SWIPER ===============*/
let swiperHome = new Swiper('.home__container', {
  loop: true,
  slidesPerView: 1,
  spaceBetween: 10,
  grabCursor: true,
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  pagination: {
    el: '.swiper-pagination',
    dynamicBullets: true,
  },
});

/*=============== REMOVE MENU MOBILE ===============*/
const navLink = document.querySelectorAll('.nav__link');

const linkAction = () => {
  const navMenu = document.getElementById('nav-menu');
  // When we click on each nav__link, we remove the show-menu class
  navMenu.classList.remove('show-menu');
};
navLink.forEach((n) => n.addEventListener('click', linkAction));

/*=============== CHANGE BACKGROUND HEADER ===============*/
const scrollHeader = () => {
  const header = document.getElementById('header');
  // Add a class if the bottom offset is greater than 50 of the viewport
  this.scrollY >= 50
    ? header.classList.add('scroll-header')
    : header.classList.remove('scroll-header');
};
window.addEventListener('scroll', scrollHeader);

/*=============== ANIMALS SWIPER ===============*/

let swiperPopular = new Swiper('.animal__container', {
  loop: true,
  spaceBetween: 24,
  slidesPerView: 'auto',
  grabCursor: true,

  pagination: {
    el: '.swiper-pagination',
    dynamicBullets: true,
  },
  breakpoints: {
    768: {
      slidesPerView: 3,
    },
    1024: {
      spaceBetween: 48,
    },
  },
});

/*=============== MIXITUP FILTER FEATURED ===============*/

/* Link active featured */

/*=============== SHOW SCROLL UP ===============*/
const scrollUp = () => {
  const scrollUp = document.getElementById('scroll-up');
  // When the scroll is higher than 350 viewport height, add the show-scroll class to the a tag with the scrollup class
  this.scrollY >= 350
    ? scrollUp.classList.add('show-scroll')
    : scrollUp.classList.remove('show-scroll');
};
window.addEventListener('scroll', scrollUp);

/*=============== SCROLL SECTIONS ACTIVE LINK ===============*/
const sections = document.querySelectorAll('section[id]');

const scrollActive = () => {
  const scrollDown = window.scrollY;

  sections.forEach((current) => {
    const sectionHeight = current.offsetHeight,
      sectionTop = current.offsetTop - 58,
      sectionId = current.getAttribute('id'),
      sectionsClass = document.querySelector(
        '.nav__menu a[href*=' + sectionId + ']'
      );

    if (scrollDown > sectionTop && scrollDown <= sectionTop + sectionHeight) {
      sectionsClass.classList.add('active-link');
    } else {
      sectionsClass.classList.remove('active-link');
    }
  });
};
window.addEventListener('scroll', scrollActive);

/*=============== SCROLL REVEAL ANIMATION ===============*/

/*=============== CHANGE PASSWORD MODAL ===============*/
const btnCloseModal = document.querySelector('.btn--close-modal');
const btnOpenModal = document.querySelector('.btn--show-modal');
const overlay = document.querySelector('.overlay');
const modal = document.querySelector('.modal');
const togglePass = document.querySelector('#togglePass');
const passwordInputs = document.querySelectorAll('.input-box .password--input');

const openModal = function (e) {
  e.preventDefault(); // to prevent the website from going back to the top when opening the modal
  modal.classList.remove('hidden');
  overlay.classList.remove('hidden');

  e.stopPropagation();
};

const closeModal = function () {
  modal.classList.add('hidden');
  overlay.classList.add('hidden');
};

btnOpenModal.addEventListener('click', openModal);

btnCloseModal.addEventListener('click', closeModal);
overlay.addEventListener('click', closeModal);

document.addEventListener('keydown', function (e) {
  if (e.key === 'Escape' && !modal.classList.contains('hidden')) {
    closeModal();
  }
});

const togglePasswordVisibility = () => {
  passwordInputs.forEach((input) => {
    input.type = togglePass.checked ? 'text' : 'password';
  });
};

togglePass.addEventListener('click', togglePasswordVisibility);

/*=============== PRE-LOADER ===============*/
const loader = document.querySelector('#preloader');
const main = document.querySelector('.main');
const header = document.querySelector('#header');
const footer = document.querySelector('.footer');

header.style.display = 'none';
main.style.display = 'none';
footer.style.display = 'none';

window.addEventListener('load', function () {
  setTimeout(function () {
    loader.style.display = 'none';
    header.style.display = 'initial';
    main.style.display = 'initial';
    footer.style.display = 'initial';
  }, 1200);
});
