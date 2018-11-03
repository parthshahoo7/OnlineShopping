/*

    Template Name: Anadi - Responsive Ecommerce HTML5 Template;
    Template URI: http://hastech.company/
    Description: This is Bootstrap4 html5 template
    Author: HasTech
    Author URI: http://hastech.company/
    Version: 1.0

*/

(function ($) {
	"use Strict";
/*---------------------------------
    Mean Menu Active
-----------------------------------*/
jQuery('.header-menu-area nav').meanmenu({
    meanMenuContainer: '.mobile-menu',
    meanScreenWidth: "991"
})
    
/*---------------------------
    Mini Cart Hover Active
----------------------------*/
$('.cart-dropdown').hide();
if( $(window).width() > 991 ) {
    $('.mini-cart > ul > li ').hover(
      function() {
        if( $(this).children('ul').size() > 0 && $(this).children().hasClass('cart-dropdown') ) {
            $(this).children().stop().slideDown(400);
        }
      }, function() {
        $(this).children('.cart-dropdown').stop().slideUp(300);
      }
    );
};
/*---------------------------------
    Sticky Menu Active
-----------------------------------*/
$(window).scroll(function() {
if ($(this).scrollTop() >50){  
    $('.header-sticky').addClass("is-sticky");
  }
  else{
    $('.header-sticky').removeClass("is-sticky");
  }
});
/*----------------------------
    Owl Active
------------------------------ */
/*-------------
    Hero Slider Active
------------------------------*/
$('.hero-slider, .hero-slider-3').owlCarousel({
    smartSpeed: 1000,
    nav: true,
    loop: true,
    animateOut: 'fadeOut',
    animateIn: 'fadeIn',
    autoplay: false,
    autoHeight:true,
    navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
    responsive: {
        0: {
            items: 1,
            nav: false,
            dots: true
        },
        768: {
            items: 1,
            nav: false,
            dots: true
        },
        991: {
            items: 1,
        }
    }
})  
/*------- 
    Product Slider Active 
----------------------------------*/
 $('.product-slider').owlCarousel({
        smartSpeed: 1000,
        nav: true,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 2
            },
            768: {
                items: 3
            },
            1000: {
                items: 4
            },
            1200: {
                items: 4
            }
        }
    })  
/*------- 
    On Sale Product Active 
----------------------------------*/
 $('.on-sale-product').owlCarousel({
        smartSpeed: 1000,
        nav: true,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 2
            },
            768: {
                items: 2
            },
            1000: {
                items: 3
            },
            1200: {
                items: 3
            }
        }
    }) 
/*------- 
    Product list Slider Active 
----------------------------------*/
 $('.product-cat-list').owlCarousel({
        smartSpeed: 1000,
        nav: true,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 1
            },
            768: {
                items: 1
            },
            1000: {
                items: 1
            },
            1200: {
                items: 1
            }
        }
    })
/*------- 
    Modal Menu Active 
----------------------------------*/
 $('.single-slide-menu').owlCarousel({
        smartSpeed: 1000,
        nav: false,
        responsive: {
            0: {
                items: 3
            },
            450: {
                items: 3
            },
            768: {
                items: 4
            },
            1000: {
                items: 4
            },
            1200: {
                items: 4
            }
        }
    });
$('.modal').on('shown.bs.modal', function (e) {
    $('.single-slide-menu').resize();
})
    
$('.single-slide-menu a').on('click',function(e){
  e.preventDefault();

  var $href = $(this).attr('href');

  $('.single-slide-menu a').removeClass('active');
  $(this).addClass('active');

  $('.product-details-large .tab-pane').removeClass('active show');
  $('.product-details-large '+ $href ).addClass('active show');

})
/*------- 
    Look Book Slider Active 
----------------------------------*/
 $('.look-book-slider').owlCarousel({
        smartSpeed: 1000,
        nav: false,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 1
            },
            1200: {
                items: 1
            },
            1600: {
                items: 2
            }
        }
    })
/*------- 
    Blog Slider Active 
----------------------------------*/
 $('.blog-slider').owlCarousel({
        smartSpeed: 1000,
        nav: true,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 1
            },
            768: {
                items: 2
            },
            1000: {
                items: 3
            },
            1200: {
                items: 3
            }
        }
    })
/*------- 
    Blog Gallery Post Active 
----------------------------------*/
 $('.post-gallery').owlCarousel({
        nav: true,
        autoplay: true,
        autoplayTimeout: 5000,
        navText: ['<i class="fa fa-caret-left"></i>', '<i class="fa fa-caret-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 1
            },
            768: {
                items: 1
            },
            1000: {
                items: 1
            },
            1200: {
                items: 1
            }
        }
    })
/*------- 
    Testimonial Active  
----------------------------------*/
 $('.testimonial-active').owlCarousel({
        smartSpeed: 1000,
        nav: false,
        navText: ['<i class="fa fa-angle-left"></i>', '<i class="fa fa-angle-right"></i>'],
        responsive: {
            0: {
                items: 1
            },
            450: {
                items: 1
            },
            768: {
                items: 1
            },
            1000: {
                items: 1
            },
            1200: {
                items: 1
            }
        }
    })
/*--------
    Brand Active
----------------------------------*/
 $('.brand-active').owlCarousel({
        smartSpeed: 1000,
        nav: true,
        navText: ['<i class="zmdi zmdi-chevron-left"></i>', '<i class="zmdi zmdi-chevron-right"></i>'],
        responsive: {
            0: {
                items: 2
            },
            450: {
                items: 3
            },
            768: {
                items: 4
            },
            1000: {
                items: 5
            },
            1200: {
                items: 6
            }
        }
}) 
/*----------------------------------- 
    Single Product Slide Menu Active 
--------------------------------------*/  
$('.product-tab-menu').slick({
		prevArrow: '<i class="fa fa-angle-up"></i>',
		nextArrow: '<i class="fa fa-angle-down slick-next-btn"></i>',
        slidesToShow: 3,
        vertical: true,
        responsive: [
            {
              breakpoint: 1200,
              settings: {
                slidesToShow: 3,
                slidesToScroll: 3
              }
            },
            {
              breakpoint: 991,
              settings: {
                slidesToShow: 3,
                slidesToScroll: 3
              }
            },
            {
              breakpoint: 480,
              settings: {
                slidesToShow: 1,
                slidesToScroll: 1
              }
            }
          ]
	});   
$('.product-tab-menu a').on('click',function(e){
      e.preventDefault();
     
      var $href = $(this).attr('href');
     
      $('.product-tab-menu a').removeClass('active');
      $(this).addClass('active');
     
      $('.single-product-img .tab-pane').removeClass('active show');
      $('.single-product-img '+ $href ).addClass('active show');
     
  })
/*----------------------------
    Tooltip Active
------------------------------ */
 $('.product-action a').tooltip({
        animated: 'fade',
        placement: 'top',
        container: 'body'
}); 
/*----------------------------------
    ScrollUp Active
-----------------------------------*/
$.scrollUp({
    scrollText: '<i class="fa fa-angle-up"></i>',
    easingType: 'linear',
    scrollSpeed: 900,
    animation: 'fade'
});
/*----------------------------------- 
    Count Down Active 
----------------------------------*/ 
$('[data-countdown]').each(function() {
	var $this = $(this), finalDate = $(this).data('countdown');
	$this.countdown(finalDate, function(event) {
		$this.html(event.strftime('<div class="single-count"><span>%D</span>D</div><div class="single-count"><span>%H</span>h</div><div class="single-count"><span>%M</span>m</div><div class="single-count"><span>%S</span>s</div>'));
	});
}); 
/*---------------------------- 
    Full Screen Menu Active
-----------------------------*/
    function fullScreenmenu() {
        var menuTrigger = $('.menu-icon button'),
            endTriggermenu = $('button.menu-close'),
            container = $('.full-screen-menu-area');
        
        menuTrigger.on('click', function() {
            container.addClass('inside');
        });
        
        endTriggermenu.on('click', function() {
            container.removeClass('inside');
        });
        
    };
    fullScreenmenu();
/*--------------------------------
    Price Slider Active
-------------------------------- */
var sliderrange = $('#slider-range');
var amountprice = $('#amount');
$(function() {
    sliderrange.slider({
        range: true,
        min: 20,
        max: 100,
        values: [0, 100],
        slide: function(event, ui) {
            amountprice.val("$" + ui.values[0] + " - $" + ui.values[1]);
        }
    });
    amountprice.val("$" + sliderrange.slider("values", 0) +
        " - $" + sliderrange.slider("values", 1));
});
/*------------------------------ 
    Nice Select Active
---------------------------------*/
$('select').niceSelect();
/*------------------------------ 
    Toggle Function Active
---------------------------------*/   
/*--- showlogin toggle function ----*/
    $('#showlogin').on('click', function() {
        $('#checkout-login').slideToggle(900);
    });

 /*--- showlogin toggle function ----*/
    $('#showcoupon').on('click', function() {
        $('#checkout_coupon').slideToggle(900);
    });
/*--- showlogin toggle function ----*/
    $('#cbox').on('click', function() {
        $('#cbox-info').slideToggle(900);
    });

 /*--- showlogin toggle function ----*/
    $('#ship-box').on('click', function() {
        $('#ship-box-info').slideToggle(1000);
    });
/*--------------------------
    Counter Up
---------------------------- */
    $('.counter').counterUp({
        delay: 70,
        time: 5000
    });
/* --------------------------------------------------------
    FAQ Accordion 
* -------------------------------------------------------*/ 
  $('.card-header a').on('click', function() {
    $('.card').removeClass('actives');
    $(this).parents('.card').addClass('actives');
  });
/* --------------------------------------------------------
    Instafeed Active
* -------------------------------------------------------*/ 
if($('#instagram-feed').length) {
    var feed = new Instafeed({
        get: 'user',
        userId: 6665768655,
        accessToken: '6665768655.1677ed0.313e6c96807c45d8900b4f680650dee5',
        target: 'instagram-feed',
        resolution: 'thumbnail',
        limit: 6,
        template: '<li><a href="{{link}}" target="_new"><img src="{{image}}" /></a></li>',
    });
    feed.run();
}
/*--------------------------
    Isotope Active
---------------------------- */
$('.protfolio-active').imagesLoaded( function() {
// images have loaded
    // init Isotope
    var $grid = $('.protfolio-active').isotope({
      // options
    });
    // filter items on button click
    $('.protfolio-menu-active').on( 'click', 'li', function() {
      var filterValue = $(this).attr('data-filter');
      $grid.isotope({ filter: filterValue });

          $(this).siblings('.active').removeClass('active');
          $(this).addClass('active');
          event.preventDefault();
    });
});
/* --------------------------------------------------------
    Venobox Active
* -------------------------------------------------------*/  
  $('.venobox').venobox({
        border: '10px',
        titleattr: 'data-title',
        numeratio: true,
        infinigall: true
    });
/*----------------------------------
    EasyZoom Active       
------------------------------------*/   
var $easyzoom = $('.easyzoom').easyZoom();    

})(jQuery);