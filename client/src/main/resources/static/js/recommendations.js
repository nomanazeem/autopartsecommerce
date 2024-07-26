const server = "http://127.0.0.1:5000/";

function initializeSlider() {
  $(".yt-content-slider-custom").each(function () {
    var $slider = $(this),
      $panels = $slider.children("div"),
      data = $slider.data();
    // Remove unwanted br's
    //$slider.children(':not(.yt-content-slide)').remove();
    // Apply Owl Carousel

    $slider.owlCarousel2({
      responsiveClass: true,
      mouseDrag: true,
      video: true,
      lazyLoad: data.lazyload == "yes" ? true : false,
      autoplay: data.autoplay == "yes" ? true : false,
      autoHeight: data.autoheight == "yes" ? true : false,
      autoplayTimeout: data.delay * 1000,
      smartSpeed: data.speed * 1000,
      autoplayHoverPause: data.hoverpause == "yes" ? true : false,
      center: data.center == "yes" ? true : false,
      loop: data.loop == "yes" ? true : false,
      dots: data.pagination == "yes" ? true : false,
      nav: data.arrows == "yes" ? true : false,
      dotClass: "owl2-dot",
      dotsClass: "owl2-dots",
      margin: data.margin,
      navText: ["", ""],
      responsive: {
        0: {
          items: data.items_column4,
        },
        480: {
          items: data.items_column3,
        },
        768: {
          items: data.items_column2,
        },
        992: {
          items: data.items_column1,
        },
        1200: {
          items: data.items_column0,
        },
        1650: {
          items: data.items_column00,
        },
      },
    });
  });
}
async function getRecommendations() {
  try {
    const keyword = $('.title-product>h1').text();
    const url = `${server}/recommend?keyword=${keyword}`;
    var response = await fetch(url);
    var products = await response.json();
    var productsHtml = getProductHtml(products);
    $("#recommendations").append(productsHtml);
    initializeSlider();
  } catch {
    alert("Something went wrong from recommendations api");
  }
}
function getProductHtml(products) {
  var html = "";
  for (let i = 0; i < products.length; i++) {
    html += `
        <div class="product-layout product-grid">
            <div class="product-item-container item&#45;&#45;static">
                <div class="left-block">
                    <div class="product-image-container second_img">
                        <a href="product.html" target="_self" title="Shoulder kevinis">
                            <img src=${server}${products[i].part_image} class="img-1 img-responsive" alt="image1">
                            <img src="image/catalog/demo/product/270/5.jpg" class="img-2 img-responsive" alt="image2">
                        </a>
                    </div>

                    <div class="so-quickview">
                        <a class="iframe-link btn-button quickview quickview_handler visible-lg" href="quickview.html" title="Quick view" data-fancybox-type="iframe"><i class="fa fa-search"></i><span>Quick view</span></a>
                    </div>
                </div>
                <div class="right-block">
                    <div class="button-group cartinfo&#45;&#45;static">

                        <button type="button" class="wishlist btn-button" title="Add to Wish List" onclick="wishlist.add('60');"><i class="fa fa-heart"></i></button>
                        <button type="button" class="addToCart" title="Add to cart" onclick="cart.add('60 ');">
                            <span>Add to cart </span>
                        </button>
                        <button type="button" class="compare btn-button" title="Compare this Product " onclick="compare.add('60');"><i class="fa fa-refresh"></i></button>
                    </div>
                    <h4><a href="product.html" title="Shoulder kevinis" target="_self">${products[i].Combined}</a></h4>
                    <div class="rating">    <span class="fa fa-stack"><i class="fa fa-star fa-stack-2x"></i></span>
                        <span class="fa fa-stack"><i class="fa fa-star fa-stack-2x"></i></span>
                        <span class="fa fa-stack"><i class="fa fa-star fa-stack-2x"></i></span>
                        <span class="fa fa-stack"><i class="fa fa-star fa-stack-2x"></i></span>
                        <span class="fa fa-stack"><i class="fa fa-star fa-stack-2x"></i></span>
                    </div>
                    <div class="price">
                        <span class="price">$60.00</span>
                    </div>
                </div>

            </div>
        </div>
              `;
  }
  return html;
}
