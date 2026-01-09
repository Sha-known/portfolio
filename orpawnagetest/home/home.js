
'use strict'

const menuToggle = document.querySelector('.menu-toggle');
const bxMenu = document.querySelector('.bx-menu');
const bxX = document.querySelector('.bx-x');

const navBar = document.querySelector('.navbar');

// --- open menu ---

bxMenu.addEventListener('click', (e)=> {
    if(e.target.classList.contains('bx-menu')){
        navBar.classList.add('show-navbar');
        bxMenu.classList.add('hide-bx');
        bxX.classList.add('show-bx');
    }
})

// --- close menu ---

bxX.addEventListener('click', (e)=> {
    if(e.target.classList.contains('bx-x')){
        navBar.classList.remove('show-navbar');
        bxMenu.classList.remove('hide-bx');
        bxX.classList.remove('show-bx');
    }
})

document.getElementById("filter-btn").addEventListener("click", function () {
    const categoryFilter = document.getElementById("category-filter").value;
    const timeFilter = document.getElementById("time-filter").value;
  
    // Select all product boxes
    const products = document.querySelectorAll(".product-box");
  
    products.forEach((product) => {
      const productCategory = product.getAttribute("data-category");
      const productTime = product.getAttribute("data-time");
  
      // Check category and time filters
      const matchesCategory =
        categoryFilter === "all" || productCategory === categoryFilter;
      const matchesTime = timeFilter === "all" || productTime === timeFilter;
  
      // Show or hide the product based on filter match
      if (matchesCategory && matchesTime) {
        product.style.display = "block";
      } else {
        product.style.display = "none";
      }
    });
  });
  