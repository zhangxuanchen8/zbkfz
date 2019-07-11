
<style>
@keyframes wrench {
	0%{transform:rotate(-12deg)}
	8%{transform:rotate(12deg)}
	10%{transform:rotate(24deg)}
	18%{transform:rotate(-24deg)}
	20%{transform:rotate(-24deg)}
	28%{transform:rotate(24deg)}
	30%{transform:rotate(24deg)}
	38%{transform:rotate(-24deg)}
	40%{transform:rotate(-24deg)}
	48%{transform:rotate(24deg)}
	50%{transform:rotate(24deg)}
	58%{transform:rotate(-24deg)}
	60%{transform:rotate(-24deg)}
	68%{transform:rotate(24deg)}
	75%,100%{transform:rotate(0deg)}
}
.faa-wrench.animated,
.faa-wrench.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-wrench {
	animation: wrench 2.5s ease infinite;
	transform-origin-x: 90%;
	transform-origin-y: 35%;
	transform-origin-z: initial;
}
.faa-wrench.animated.faa-fast,
.faa-wrench.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-wrench.faa-fast {
	animation: wrench 1.2s ease infinite;
}
.faa-wrench.animated.faa-slow,
.faa-wrench.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-wrench.faa-slow {
	animation: wrench 3.7s ease infinite;
}

/* BELL */
@keyframes ring {
	0%{transform:rotate(-15deg)}
	2%{transform:rotate(15deg)}
	4%{transform:rotate(-18deg)}
	6%{transform:rotate(18deg)}
	8%{transform:rotate(-22deg)}
	10%{transform:rotate(22deg)}
	12%{transform:rotate(-18deg)}
	14%{transform:rotate(18deg)}
	16%{transform:rotate(-12deg)}
	18%{transform:rotate(12deg)}
	20%,100%{transform:rotate(0deg)}
}
.faa-ring.animated,
.faa-ring.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-ring {
	animation: ring 2s ease infinite;
	transform-origin-x: 50%;
	transform-origin-y: 0px;
	transform-origin-z: initial;
}
.faa-ring.animated.faa-fast,
.faa-ring.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-ring.faa-fast {
	animation: ring 1s ease infinite;
}
.faa-ring.animated.faa-slow,
.faa-ring.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-ring.faa-slow {
	animation: ring 3s ease infinite;
}

/* VERTICAL */
@keyframes vertical {
	0%{transform:translate(0,-3px)}
	4%{transform:translate(0,3px)}
	8%{transform:translate(0,-3px)}
	12%{transform:translate(0,3px)}
	16%{transform:translate(0,-3px)}
	20%{transform:translate(0,3px)}
	22%,100%{transform:translate(0,0)}
}
.faa-vertical.animated,
.faa-vertical.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-vertical {
	animation: vertical 2s ease infinite;
}
.faa-vertical.animated.faa-fast,
.faa-vertical.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-vertical.faa-fast {
	animation: vertical 1s ease infinite;
}
.faa-vertical.animated.faa-slow,
.faa-vertical.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-vertical.faa-slow {
	animation: vertical 4s ease infinite;
}

/* HORIZONTAL */
@keyframes horizontal {
	0%{transform:translate(0,0)}
	6%{transform:translate(5px,0)}
	12%{transform:translate(0,0)}
	18%{transform:translate(5px,0)}
	24%{transform:translate(0,0)}
	30%{transform:translate(5px,0)}
	36%,100%{transform:translate(0,0)}
}
.faa-horizontal.animated,
.faa-horizontal.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-horizontal {
	animation: horizontal 2s ease infinite;
}
.faa-horizontal.animated.faa-fast,
.faa-horizontal.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-horizontal.faa-fast {
	animation: horizontal 1s ease infinite;
}
.faa-horizontal.animated.faa-slow,
.faa-horizontal.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-horizontal.faa-slow {
	animation: horizontal 3s ease infinite;
}

/* FLASHING */
@keyframes flash {
	0%,100%,50%{opacity:1}
	25%,75%{opacity:0}
}
.faa-flash.animated,
.faa-flash.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-flash {
	animation: flash 2s ease infinite;
}
.faa-flash.animated.faa-fast,
.faa-flash.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-flash.faa-fast {
	animation: flash 1s ease infinite;
}
.faa-flash.animated.faa-slow,
.faa-flash.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-flash.faa-slow {
	animation: flash 3s ease infinite;
}

/* BOUNCE */
@keyframes bounce {
	0%,10%,20%,50%,80%,100%{transform:translateY(0)}
	40%{transform:translateY(-15px)}
	60%{transform:translateY(-15px)}
}
.faa-bounce.animated,
.faa-bounce.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-bounce {
	animation: bounce 2s ease infinite;
}
.faa-bounce.animated.faa-fast,
.faa-bounce.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-bounce.faa-fast {
	animation: bounce 1s ease infinite;
}
.faa-bounce.animated.faa-slow,
.faa-bounce.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-bounce.faa-slow {
	animation: bounce 3s ease infinite;
}

/* SPIN */
@keyframes spin{
	0%{transform:rotate(0deg)}
	100%{transform:rotate(359deg)}
}
.faa-spin.animated,
.faa-spin.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-spin {
	animation: spin 1.5s linear infinite;
}
.faa-spin.animated.faa-fast,
.faa-spin.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-spin.faa-fast {
	animation: spin 0.7s linear infinite;
}
.faa-spin.animated.faa-slow,
.faa-spin.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-spin.faa-slow {
	animation: spin 2.2s linear infinite;
}

/* FLOAT */
@keyframes float{
	0%{transform: translateY(0)}
	50%{transform: translateY(-6px)}
	100%{transform: translateY(0)}
}
.faa-float.animated,
.faa-float.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-float {
	animation: float 2s linear infinite;
}
.faa-float.animated.faa-fast,
.faa-float.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-float.faa-fast {
	animation: float 1s linear infinite;
}
.faa-float.animated.faa-slow,
.faa-float.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-float.faa-slow {
	animation: float 3s linear infinite;
}

/* PULSE */
@keyframes pulse {
	0% {transform: scale(1.1)}
 	50% {transform: scale(0.8)}
 	100% {transform: scale(1.1)}
}
.faa-pulse.animated,
.faa-pulse.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-pulse {
	animation: pulse 2s linear infinite;
}
.faa-pulse.animated.faa-fast,
.faa-pulse.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-pulse.faa-fast {
	animation: pulse 1s linear infinite;
}
.faa-pulse.animated.faa-slow,
.faa-pulse.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-pulse.faa-slow {
	animation: pulse 3s linear infinite;
}

/* SHAKE */
.faa-shake.animated,
.faa-shake.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-shake {
	animation: wrench 2.5s ease infinite;
}
.faa-shake.animated.faa-fast,
.faa-shake.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-shake.faa-fast {
	animation: wrench 1.2s ease infinite;
}
.faa-shake.animated.faa-slow,
.faa-shake.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-shake.faa-slow {
	animation: wrench 3.7s ease infinite;
}

/* TADA */
@keyframes tada {
	0% {transform: scale(1)}
	10%,20% {transform:scale(.9) rotate(-8deg);}
	30%,50%,70% {transform:scale(1.3) rotate(8deg)}
	40%,60% {transform:scale(1.3) rotate(-8deg)}
	80%,100% {transform:scale(1) rotate(0)}
}

.faa-tada.animated,
.faa-tada.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-tada {
	animation: tada 2s linear infinite;
}
.faa-tada.animated.faa-fast,
.faa-tada.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-tada.faa-fast {
	animation: tada 1s linear infinite;
}
.faa-tada.animated.faa-slow,
.faa-tada.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-tada.faa-slow {
	animation: tada 3s linear infinite;
}

/* PASSING */
@keyframes passing {
	0% {transform:translateX(-50%); opacity:0}
	50% {transform:translateX(0%); opacity:1}
	100% {transform:translateX(50%); opacity:0}
}

.faa-passing.animated,
.faa-passing.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-passing {
	animation: passing 2s linear infinite;
}
.faa-passing.animated.faa-fast,
.faa-passing.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-passing.faa-fast {
	animation: passing 1s linear infinite;
}
.faa-passing.animated.faa-slow,
.faa-passing.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-passing.faa-slow {
	animation: passing 3s linear infinite;
}

/* PASSING REVERSE */

@keyframes passing-reverse {
	0% {transform:translateX(50%); opacity:0}
	50% {transform:translateX(0%); opacity:1}
	100% {transform:translateX(-50%); opacity:0}
}

.faa-passing-reverse.animated,
.faa-passing-reverse.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-passing-reverse {
	animation: passing-reverse 2s linear infinite;
}
.faa-passing-reverse.animated.faa-fast,
.faa-passing-reverse.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-passing-reverse.faa-fast {
	animation: passing-reverse 1s linear infinite;
}
.faa-passing-reverse.animated.faa-slow,
.faa-passing-reverse.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-passing-reverse.faa-slow {
	animation: passing-reverse 3s linear infinite;
}

/* WAVE */
@keyframes burst {
	0% {opacity:.6}
	50% {transform:scale(1.8);opacity:0}
	100%{opacity:0}
}
.faa-burst.animated,
.faa-burst.animated-hover:hover,
.faa-parent.animated-hover:hover > .faa-burst {
	animation: burst 2s infinite linear
}
.faa-burst.animated.faa-fast,
.faa-burst.animated-hover.faa-fast:hover,
.faa-parent.animated-hover:hover > .faa-burst.faa-fast {
	animation: burst 1s infinite linear
}
.faa-burst.animated.faa-slow,
.faa-burst.animated-hover.faa-slow:hover,
.faa-parent.animated-hover:hover > .faa-burst.faa-slow {
	animation: burst 3s infinite linear
}
</style>


<div class="pageContent" >
<div style="overflow: auto;width: 1707px;;height: 838px">

<i class="fa fa-file-code-o fa-2x fa-spin fa-refresh"></i>
<i class="fa fa-vine fa-2x faa-passing animated"></i>
<i class="fa fa-codepen fa-2x faa-passing-reverse animated"></i>
<i class="fa fa-jsfiddle fa-2x faa-tada animated"></i>
<i class="fa fa-life-bouy fa-2x"></i>
<i class="fa fa-life-buoy fa-2x"></i>
<i class="fa fa-life-saver fa-2x"></i>
<i class="fa fa-support fa-2x"></i>
<i class="fa fa-life-ring fa-2x"></i>
<i class="fa fa-circle-o-notch fa-2x"></i>
<i class="fa fa-ra fa-2x"></i>
<i class="fa fa-rebel fa-2x"></i>
<i class="fa fa-ge fa-2x"></i>
<i class="fa fa-empire fa-2x"></i>
<i class="fa fa-git-square fa-2x"></i>
<i class="fa fa-git fa-2x"></i>
<i class="fa fa-y-combinator-square fa-2x"></i>
<i class="fa fa-yc-square fa-2x"></i>
<i class="fa fa-hacker-news fa-2x"></i>
<i class="fa fa-tencent-weibo fa-2x"></i>
<i class="fa fa-qq fa-2x"></i>
<i class="fa fa-wechat fa-2x"></i>
<i class="fa fa-weixin fa-2x"></i>
<i class="fa fa-send fa-2x"></i>
<i class="fa fa-paper-plane fa-2x"></i>
<i class="fa fa-send-o fa-2x"></i>
<i class="fa fa-paper-plane-o fa-2x"></i>
<i class="fa fa-history fa-2x"></i>
<i class="fa fa-circle-thin fa-2x"></i>
<i class="fa fa-header fa-2x"></i>
<i class="fa fa-paragraph fa-2x"></i>
<i class="fa fa-sliders fa-2x"></i>
<i class="fa fa-share-alt fa-2x"></i>
<i class="fa fa-share-alt-square fa-2x"></i>
<i class="fa fa-bomb fa-2x"></i>
<i class="fa fa-soccer-ball-o fa-2x"></i>
<i class="fa fa-futbol-o fa-2x"></i>
<i class="fa fa-tty fa-2x"></i>
<i class="fa fa-binoculars fa-2x"></i>
<i class="fa fa-plug fa-2x"></i>
<i class="fa fa-slideshare fa-2x"></i>
<i class="fa fa-twitch fa-2x"></i>
<i class="fa fa-yelp fa-2x"></i>
<i class="fa fa-newspaper-o fa-2x"></i>
<i class="fa fa-wifi fa-2x"></i>
<i class="fa fa-calculator fa-2x"></i>
<i class="fa fa-paypal fa-2x"></i>
<i class="fa fa-google-wallet fa-2x"></i>
<i class="fa fa-cc-visa fa-2x"></i>
<i class="fa fa-cc-mastercard fa-2x"></i>
<i class="fa fa-cc-discover fa-2x"></i>
<i class="fa fa-cc-amex fa-2x"></i>
<i class="fa fa-cc-paypal fa-2x"></i>
<i class="fa fa-cc-stripe fa-2x"></i>
<i class="fa fa-bell-slash fa-2x"></i>
<i class="fa fa-bell-slash-o fa-2x"></i>
<i class="fa fa-trash fa-2x"></i>
<i class="fa fa-copyright fa-2x"></i>
<i class="fa fa-at fa-2x"></i>
<i class="fa fa-eyedropper fa-2x"></i>
<i class="fa fa-paint-brush fa-2x"></i>
<i class="fa fa-birthday-cake fa-2x"></i>
<i class="fa fa-area-chart fa-2x"></i>
<i class="fa fa-pie-chart fa-2x"></i>
<i class="fa fa-line-chart fa-2x"></i>
<i class="fa fa-lastfm fa-2x"></i>
<i class="fa fa-lastfm-square fa-2x"></i>
<i class="fa fa-toggle-off fa-2x"></i>
<i class="fa fa-toggle-on fa-2x"></i>
<i class="fa fa-bicycle fa-2x"></i>
<i class="fa fa-bus fa-2x"></i>
<i class="fa fa-ioxhost fa-2x"></i>
<i class="fa fa-angellist fa-2x"></i>
<i class="fa fa-cc fa-2x"></i>
<i class="fa fa-shekel fa-2x"></i>
<i class="fa fa-sheqel fa-2x"></i>
<i class="fa fa-ils fa-2x"></i>
<i class="fa fa-meanpath fa-2x"></i>
<i class="fa fa-buysellads fa-2x"></i>
<i class="fa fa-connectdevelop fa-2x"></i>
<i class="fa fa-dashcube fa-2x"></i>
<i class="fa fa-forumbee fa-2x"></i>
<i class="fa fa-leanpub fa-2x"></i>
<i class="fa fa-sellsy fa-2x"></i>
<i class="fa fa-shirtsinbulk fa-2x"></i>
<i class="fa fa-simplybuilt fa-2x"></i>
<i class="fa fa-skyatlas fa-2x"></i>
<i class="fa fa-cart-plus fa-2x"></i>
<i class="fa fa-cart-arrow-down fa-2x"></i>
<i class="fa fa-diamond fa-2x"></i>
<i class="fa fa-ship fa-2x"></i>
<i class="fa fa-user-secret fa-2x"></i>
<i class="fa fa-motorcycle fa-2x"></i>
<i class="fa fa-street-view fa-2x"></i>
<i class="fa fa-heartbeat fa-2x"></i>
<i class="fa fa-venus fa-2x"></i>
<i class="fa fa-mars fa-2x"></i>
<i class="fa fa-mercury fa-2x"></i>
<i class="fa fa-intersex fa-2x"></i>
<i class="fa fa-transgender fa-2x"></i>
<i class="fa fa-transgender-alt fa-2x"></i>
<i class="fa fa-mars-double fa-2x"></i>
<i class="fa fa-venus-mars fa-2x"></i>
<i class="fa fa-mars-stroke fa-2x"></i>
<i class="fa fa-mars-stroke-v fa-2x"></i>
<i class="fa fa-mars-stroke-h fa-2x"></i>
<i class="fa fa-neuter fa-2x"></i>
<i class="fa fa-genderless fa-2x"></i>
<i class="fa fa-facebook-official fa-2x"></i>
<i class="fa fa-pinterest-p fa-2x"></i>
<i class="fa fa-whatsapp fa-2x"></i>
<i class="fa fa-server fa-2x"></i>
<i class="fa fa-user-plus fa-2x"></i>
<i class="fa fa-user-times fa-2x"></i>
<i class="fa fa-hotel fa-2x"></i>
<i class="fa fa-bed fa-2x"></i>
<i class="fa fa-viacoin fa-2x"></i>
<i class="fa fa-train fa-2x"></i>
<i class="fa fa-subway fa-2x"></i>
<i class="fa fa-medium fa-2x"></i>
<i class="fa fa-yc fa-2x"></i>
<i class="fa fa-y-combinator fa-2x"></i>
<i class="fa fa-optin-monster fa-2x"></i>
<i class="fa fa-opencart fa-2x"></i>
<i class="fa fa-expeditedssl fa-2x"></i>
<i class="fa fa-battery-4 fa-2x"></i>
<i class="fa fa-battery-full fa-2x"></i>
<i class="fa fa-battery-3 fa-2x"></i>
<i class="fa fa-battery-three-quarters fa-2x"></i>
<i class="fa fa-battery-2 fa-2x"></i>
<i class="fa fa-battery-half fa-2x"></i>
<i class="fa fa-battery-1 fa-2x"></i>
<i class="fa fa-battery-quarter fa-2x"></i>
<i class="fa fa-battery-0 fa-2x"></i>
<i class="fa fa-battery-empty fa-2x"></i>
<i class="fa fa-mouse-pointer fa-2x"></i>
<i class="fa fa-i-cursor fa-2x"></i>
<i class="fa fa-object-group fa-2x"></i>
<i class="fa fa-object-ungroup fa-2x"></i>
<i class="fa fa-sticky-note fa-2x"></i>
<i class="fa fa-sticky-note-o fa-2x"></i>
<i class="fa fa-cc-jcb fa-2x"></i>
<i class="fa fa-cc-diners-club fa-2x"></i>
<i class="fa fa-clone fa-2x"></i>
<i class="fa fa-balance-scale fa-2x"></i>
<i class="fa fa-hourglass-o fa-2x"></i>
<i class="fa fa-hourglass-1 fa-2x"></i>
<i class="fa fa-hourglass-start fa-2x"></i>
<i class="fa fa-hourglass-2 fa-2x"></i>
<i class="fa fa-hourglass-half fa-2x"></i>
<i class="fa fa-hourglass-3 fa-2x"></i>
<i class="fa fa-hourglass-end fa-2x"></i>
<i class="fa fa-hourglass fa-2x"></i>
<i class="fa fa-hand-grab-o fa-2x"></i>
<i class="fa fa-hand-rock-o fa-2x"></i>
<i class="fa fa-hand-stop-o fa-2x"></i>
<i class="fa fa-hand-paper-o fa-2x"></i>
<i class="fa fa-hand-scissors-o fa-2x"></i>
<i class="fa fa-hand-lizard-o fa-2x"></i>
<i class="fa fa-hand-spock-o fa-2x"></i>
<i class="fa fa-hand-pointer-o fa-2x"></i>
<i class="fa fa-hand-peace-o fa-2x"></i>
<i class="fa fa-trademark fa-2x"></i>
<i class="fa fa-registered fa-2x"></i>
<i class="fa fa-creative-commons fa-2x"></i>
<i class="fa fa-gg fa-2x"></i>
<i class="fa fa-gg-circle fa-2x"></i>
<i class="fa fa-tripadvisor fa-2x"></i>
<i class="fa fa-odnoklassniki fa-2x"></i>
<i class="fa fa-odnoklassniki-square fa-2x"></i>
<i class="fa fa-get-pocket fa-2x"></i>
<i class="fa fa-wikipedia-w fa-2x"></i>
<i class="fa fa-safari fa-2x"></i>
<i class="fa fa-chrome fa-2x"></i>
<i class="fa fa-firefox fa-2x"></i>
<i class="fa fa-opera fa-2x"></i>
<i class="fa fa-internet-explorer fa-2x"></i>
<i class="fa fa-tv fa-2x"></i>
<i class="fa fa-television fa-2x"></i>
<i class="fa fa-contao fa-2x"></i>
<i class="fa fa-500px fa-2x"></i>
<i class="fa fa-amazon fa-2x"></i>
<i class="fa fa-calendar-plus-o fa-2x"></i>
<i class="fa fa-calendar-minus-o fa-2x"></i>
<i class="fa fa-calendar-times-o fa-2x"></i>
<i class="fa fa-calendar-check-o fa-2x"></i>
<i class="fa fa-industry fa-2x"></i>
<i class="fa fa-map-pin fa-2x"></i>
<i class="fa fa-venus-double fa-2x"></i>
<i class="fa fa-map-signs fa-2x"></i>
<i class="fa fa-map-o fa-2x"></i>
<i class="fa fa-map fa-2x"></i>
<i class="fa fa-commenting fa-2x"></i>
<i class="fa fa-commenting-o fa-2x"></i>
<i class="fa fa-houzz fa-2x"></i>
<i class="fa fa-vimeo fa-2x"></i>
<i class="fa fa-black-tie fa-2x"></i>
<i class="fa fa-fonticons fa-2x"></i>
<i class="fa fa-glass fa-2x"></i>
<i class="fa fa-music fa-2x"></i>
<i class="fa fa-search fa-2x"></i>
<i class="fa fa-envelope-o fa-2x"></i>
<i class="fa fa-heart fa-2x"></i>
<i class="fa fa-star fa-2x"></i>
<i class="fa fa-star-o fa-2x"></i>
<i class="fa fa-user fa-2x"></i>
<i class="fa fa-film fa-2x"></i>
<i class="fa fa-th-large fa-2x"></i>
<i class="fa fa-th fa-2x"></i>
<i class="fa fa-th-list fa-2x"></i>
<i class="fa fa-check fa-2x"></i>
<i class="fa fa-remove fa-2x"></i>
<i class="fa fa-close fa-2x"></i>
<i class="fa fa-times fa-2x"></i>
<i class="fa fa-search-plus fa-2x"></i>
<i class="fa fa-search-minus fa-2x"></i>
<i class="fa fa-power-off fa-2x"></i>
<i class="fa fa-signal fa-2x"></i>
<i class="fa fa-gear fa-2x"></i>
<i class="fa fa-cog fa-2x"></i>
<i class="fa fa-trash-o fa-2x"></i>
<i class="fa fa-home fa-2x"></i>
<i class="fa fa-file-o fa-2x"></i>
<i class="fa fa-clock-o fa-2x"></i>
<i class="fa fa-road fa-2x"></i>
<i class="fa fa-download fa-2x"></i>
<i class="fa fa-arrow-circle-o-down fa-2x"></i>
<i class="fa fa-arrow-circle-o-up fa-2x"></i>
<i class="fa fa-inbox fa-2x"></i>
<i class="fa fa-play-circle-o fa-2x"></i>
<i class="fa fa-rotate-right fa-2x"></i>
<i class="fa fa-repeat fa-2x"></i>
<i class="fa fa-refresh fa-2x"></i>
<i class="fa fa-list-alt fa-2x"></i>
<i class="fa fa-lock fa-2x"></i>
<i class="fa fa-flag fa-2x"></i>
<i class="fa fa-headphones fa-2x"></i>
<i class="fa fa-volume-off fa-2x"></i>
<i class="fa fa-volume-down fa-2x"></i>
<i class="fa fa-volume-up fa-2x"></i>
<i class="fa fa-qrcode fa-2x"></i>
<i class="fa fa-barcode fa-2x"></i>
<i class="fa fa-tag fa-2x"></i>
<i class="fa fa-tags fa-2x"></i>
<i class="fa fa-book fa-2x"></i>
<i class="fa fa-bookmark fa-2x"></i>
<i class="fa fa-print fa-2x"></i>
<i class="fa fa-camera fa-2x"></i>
<i class="fa fa-font fa-2x"></i>
<i class="fa fa-bold fa-2x"></i>
<i class="fa fa-italic fa-2x"></i>
<i class="fa fa-text-height fa-2x"></i>
<i class="fa fa-text-width fa-2x"></i>
<i class="fa fa-align-left fa-2x"></i>
<i class="fa fa-align-center fa-2x"></i>
<i class="fa fa-align-right fa-2x"></i>
<i class="fa fa-align-justify fa-2x"></i>
<i class="fa fa-list fa-2x"></i>
<i class="fa fa-dedent fa-2x"></i>
<i class="fa fa-outdent fa-2x"></i>
<i class="fa fa-indent fa-2x"></i>
<i class="fa fa-video-camera fa-2x"></i>
<i class="fa fa-photo fa-2x"></i>
<i class="fa fa-image fa-2x"></i>
<i class="fa fa-picture-o fa-2x"></i>
<i class="fa fa-pencil fa-2x"></i>
<i class="fa fa-map-marker fa-2x"></i>
<i class="fa fa-adjust fa-2x"></i>
<i class="fa fa-tint fa-2x"></i>
<i class="fa fa-edit fa-2x"></i>
<i class="fa fa-pencil-square-o fa-2x"></i>
<i class="fa fa-share-square-o fa-2x"></i>
<i class="fa fa-check-square-o fa-2x"></i>
<i class="fa fa-arrows fa-2x"></i>
<i class="fa fa-step-backward fa-2x"></i>
<i class="fa fa-fast-backward fa-2x"></i>
<i class="fa fa-backward fa-2x"></i>
<i class="fa fa-play fa-2x"></i>
<i class="fa fa-pause fa-2x"></i>
<i class="fa fa-stop fa-2x"></i>
<i class="fa fa-forward fa-2x"></i>
<i class="fa fa-fast-forward fa-2x"></i>
<i class="fa fa-step-forward fa-2x"></i>
<i class="fa fa-eject fa-2x"></i>
<i class="fa fa-chevron-left fa-2x"></i>
<i class="fa fa-chevron-right fa-2x"></i>
<i class="fa fa-plus-circle fa-2x"></i>
<i class="fa fa-minus-circle fa-2x"></i>
<i class="fa fa-times-circle fa-2x"></i>
<i class="fa fa-check-circle fa-2x"></i>
<i class="fa fa-question-circle fa-2x"></i>
<i class="fa fa-info-circle fa-2x"></i>
<i class="fa fa-crosshairs fa-2x"></i>
<i class="fa fa-check-circle-o fa-2x"></i>
<i class="fa fa-ban fa-2x"></i>
<i class="fa fa-arrow-left fa-2x"></i>
<i class="fa fa-arrow-right fa-2x"></i>
<i class="fa fa-arrow-up fa-2x"></i>
<i class="fa fa-arrow-down fa-2x"></i>
<i class="fa fa-mail-forward fa-2x"></i>
<i class="fa fa-share fa-2x"></i>
<i class="fa fa-expand fa-2x"></i>
<i class="fa fa-compress fa-2x"></i>
<i class="fa fa-plus fa-2x"></i>
<i class="fa fa-minus fa-2x"></i>
<i class="fa fa-asterisk fa-2x"></i>
<i class="fa fa-exclamation-circle fa-2x"></i>
<i class="fa fa-gift fa-2x"></i>
<i class="fa fa-leaf fa-2x"></i>
<i class="fa fa-fire fa-2x"></i>
<i class="fa fa-eye fa-2x"></i>
<i class="fa fa-eye-slash fa-2x"></i>
<i class="fa fa-warning fa-2x"></i>
<i class="fa fa-exclamation-triangle fa-2x"></i>
<i class="fa fa-plane fa-2x"></i>
<i class="fa fa-calendar fa-2x"></i>
<i class="fa fa-random fa-2x"></i>
<i class="fa fa-comment fa-2x"></i>
<i class="fa fa-magnet fa-2x"></i>
<i class="fa fa-chevron-up fa-2x"></i>
<i class="fa fa-chevron-down fa-2x"></i>
<i class="fa fa-retweet fa-2x"></i>
<i class="fa fa-shopping-cart fa-2x"></i>
<i class="fa fa-folder fa-2x"></i>
<i class="fa fa-folder-open fa-2x"></i>
<i class="fa fa-arrows-v fa-2x"></i>
<i class="fa fa-arrows-h fa-2x"></i>
<i class="fa fa-bar-chart-o fa-2x"></i>
<i class="fa fa-bar-chart fa-2x"></i>
<i class="fa fa-twitter-square fa-2x"></i>
<i class="fa fa-facebook-square fa-2x"></i>
<i class="fa fa-camera-retro fa-2x"></i>
<i class="fa fa-key fa-2x"></i>
<i class="fa fa-gears fa-2x"></i>
<i class="fa fa-cogs fa-2x"></i>
<i class="fa fa-comments fa-2x"></i>
<i class="fa fa-thumbs-o-up fa-2x"></i>
<i class="fa fa-thumbs-o-down fa-2x"></i>
<i class="fa fa-star-half fa-2x"></i>
<i class="fa fa-heart-o fa-2x"></i>
<i class="fa fa-sign-out fa-2x"></i>
<i class="fa fa-linkedin-square fa-2x"></i>
<i class="fa fa-thumb-tack fa-2x"></i>
<i class="fa fa-external-link fa-2x"></i>
<i class="fa fa-sign-in fa-2x"></i>
<i class="fa fa-trophy fa-2x"></i>
<i class="fa fa-github-square fa-2x"></i>
<i class="fa fa-upload fa-2x"></i>
<i class="fa fa-lemon-o fa-2x"></i>
<i class="fa fa-phone fa-2x"></i>
<i class="fa fa-square-o fa-2x"></i>
<i class="fa fa-bookmark-o fa-2x"></i>
<i class="fa fa-phone-square fa-2x"></i>
<i class="fa fa-twitter fa-2x"></i>
<i class="fa fa-facebook-f fa-2x"></i>
<i class="fa fa-facebook fa-2x"></i>
<i class="fa fa-github fa-2x"></i>
<i class="fa fa-unlock fa-2x"></i>
<i class="fa fa-credit-card fa-2x"></i>
<i class="fa fa-feed fa-2x"></i>
<i class="fa fa-rss fa-2x"></i>
<i class="fa fa-hdd-o fa-2x"></i>
<i class="fa fa-bullhorn fa-2x"></i>
<i class="fa fa-bell fa-2x faa-tada animated"></i>
<i class="fa fa-certificate fa-2x"></i>
<i class="fa fa-hand-o-right fa-2x"></i>
<i class="fa fa-hand-o-left fa-2x"></i>
<i class="fa fa-hand-o-up fa-2x"></i>
<i class="fa fa-hand-o-down fa-2x"></i>
<i class="fa fa-arrow-circle-left fa-2x"></i>
<i class="fa fa-arrow-circle-right fa-2x"></i>
<i class="fa fa-arrow-circle-up fa-2x"></i>
<i class="fa fa-arrow-circle-down fa-2x"></i>
<i class="fa fa-globe fa-2x"></i>
<i class="fa fa-wrench fa-2x"></i>
<i class="fa fa-tasks fa-2x"></i>
<i class="fa fa-filter fa-2x"></i>
<i class="fa fa-briefcase fa-2x"></i>
<i class="fa fa-arrows-alt fa-2x"></i>
<i class="fa fa-group fa-2x"></i>
<i class="fa fa-users fa-2x"></i>
<i class="fa fa-chain fa-2x"></i>
<i class="fa fa-link fa-2x"></i>
<i class="fa fa-cloud fa-2x"></i>
<i class="fa fa-flask fa-2x"></i>
<i class="fa fa-times-circle-o fa-2x"></i>
<i class="fa fa-cut fa-2x"></i>
<i class="fa fa-scissors fa-2x"></i>
<i class="fa fa-copy fa-2x"></i>
<i class="fa fa-files-o fa-2x"></i>
<i class="fa fa-paperclip fa-2x"></i>
<i class="fa fa-save fa-2x"></i>
<i class="fa fa-floppy-o fa-2x"></i>
<i class="fa fa-square fa-2x"></i>
<i class="fa fa-navicon fa-2x"></i>
<i class="fa fa-reorder fa-2x"></i>
<i class="fa fa-bars fa-2x"></i>
<i class="fa fa-list-ul fa-2x"></i>
<i class="fa fa-list-ol fa-2x"></i>
<i class="fa fa-strikethrough fa-2x"></i>
<i class="fa fa-underline fa-2x"></i>
<i class="fa fa-table fa-2x"></i>
<i class="fa fa-magic fa-2x"></i>
<i class="fa fa-truck fa-2x"></i>
<i class="fa fa-pinterest fa-2x"></i>
<i class="fa fa-pinterest-square fa-2x"></i>
<i class="fa fa-google-plus-square fa-2x"></i>
<i class="fa fa-google-plus fa-2x"></i>
<i class="fa fa-money fa-2x"></i>
<i class="fa fa-caret-down fa-2x"></i>
<i class="fa fa-caret-up fa-2x"></i>
<i class="fa fa-caret-left fa-2x"></i>
<i class="fa fa-caret-right fa-2x"></i>
<i class="fa fa-columns fa-2x"></i>
<i class="fa fa-unsorted fa-2x"></i>
<i class="fa fa-sort fa-2x"></i>
<i class="fa fa-sort-down fa-2x"></i>
<i class="fa fa-sort-desc fa-2x"></i>
<i class="fa fa-sort-up fa-2x"></i>
<i class="fa fa-sort-asc fa-2x"></i>
<i class="fa fa-envelope fa-2x"></i>
<i class="fa fa-linkedin fa-2x"></i>
<i class="fa fa-rotate-left fa-2x"></i>
<i class="fa fa-undo fa-2x"></i>
<i class="fa fa-legal fa-2x"></i>
<i class="fa fa-gavel fa-2x"></i>
<i class="fa fa-dashboard fa-2x"></i>
<i class="fa fa-tachometer fa-2x"></i>
<i class="fa fa-comment-o fa-2x"></i>
<i class="fa fa-comments-o fa-2x"></i>
<i class="fa fa-flash fa-2x"></i>
<i class="fa fa-bolt fa-2x"></i>
<i class="fa fa-sitemap fa-2x"></i>
<i class="fa fa-umbrella fa-2x"></i>
<i class="fa fa-paste fa-2x"></i>
<i class="fa fa-clipboard fa-2x"></i>
<i class="fa fa-lightbulb-o fa-2x"></i>
<i class="fa fa-exchange fa-2x"></i>
<i class="fa fa-cloud-download fa-2x"></i>
<i class="fa fa-cloud-upload fa-2x"></i>
<i class="fa fa-user-md fa-5x" style="color:#c7c7e2; "></i>
<i class="fa fa-stethoscope fa-2x"></i>
<i class="fa fa-suitcase fa-2x"></i>
<i class="fa fa-bell-o fa-2x"></i>
<i class="fa fa-coffee fa-2x"></i>
<i class="fa fa-cutlery fa-2x"></i>
<i class="fa fa-file-text-o fa-2x"></i>
<i class="fa fa-building-o fa-2x"></i>
<i class="fa fa-hospital-o fa-2x"></i>
<i class="fa fa-ambulance fa-2x"></i>
<i class="fa fa-medkit fa-2x"></i>
<i class="fa fa-fighter-jet fa-2x"></i>
<i class="fa fa-beer fa-2x"></i>
<i class="fa fa-h-square fa-2x"></i>
<i class="fa fa-plus-square fa-2x"></i>
<i class="fa fa-angle-double-left fa-2x"></i>
<i class="fa fa-angle-double-right fa-2x"></i>
<i class="fa fa-angle-double-up fa-2x"></i>
<i class="fa fa-angle-double-down fa-2x"></i>
<i class="fa fa-angle-left fa-2x"></i>
<i class="fa fa-angle-right fa-2x"></i>
<i class="fa fa-angle-up fa-2x"></i>
<i class="fa fa-angle-down fa-2x"></i>
<i class="fa fa-desktop fa-2x"></i>
<i class="fa fa-laptop fa-2x"></i>
<i class="fa fa-tablet fa-2x"></i>
<i class="fa fa-mobile-phone fa-2x"></i>
<i class="fa fa-mobile fa-2x"></i>
<i class="fa fa-circle-o fa-2x"></i>
<i class="fa fa-quote-left fa-2x"></i>
<i class="fa fa-quote-right fa-2x"></i>
<i class="fa fa-spinner fa-2x"></i>
<i class="fa fa-circle fa-2x"></i>
<i class="fa fa-mail-reply fa-2x"></i>
<i class="fa fa-reply fa-2x"></i>
<i class="fa fa-github-alt fa-2x"></i>
<i class="fa fa-folder-o fa-2x"></i>
<i class="fa fa-folder-open-o fa-2x"></i>
<i class="fa fa-smile-o fa-2x"></i>
<i class="fa fa-frown-o fa-2x"></i>
<i class="fa fa-meh-o fa-2x"></i>
<i class="fa fa-gamepad fa-2x"></i>
<i class="fa fa-keyboard-o fa-2x"></i>
<i class="fa fa-flag-o fa-2x"></i>
<i class="fa fa-flag-checkered fa-2x"></i>
<i class="fa fa-terminal fa-2x"></i>
<i class="fa fa-code fa-2x"></i>
<i class="fa fa-mail-reply-all fa-2x"></i>
<i class="fa fa-reply-all fa-2x"></i>
<i class="fa fa-star-half-empty fa-2x"></i>
<i class="fa fa-star-half-full fa-2x"></i>
<i class="fa fa-star-half-o fa-2x"></i>
<i class="fa fa-location-arrow fa-2x"></i>
<i class="fa fa-crop fa-2x"></i>
<i class="fa fa-code-fork fa-2x"></i>
<i class="fa fa-unlink fa-2x"></i>
<i class="fa fa-chain-broken fa-2x"></i>
<i class="fa fa-question fa-2x"></i>
<i class="fa fa-info fa-2x"></i>
<i class="fa fa-exclamation fa-2x"></i>
<i class="fa fa-superscript fa-2x"></i>
<i class="fa fa-subscript fa-2x"></i>
<i class="fa fa-eraser fa-2x"></i>
<i class="fa fa-puzzle-piece fa-2x"></i>
<i class="fa fa-microphone fa-2x"></i>
<i class="fa fa-microphone-slash fa-2x"></i>
<i class="fa fa-shield fa-2x"></i>
<i class="fa fa-calendar-o fa-2x"></i>
<i class="fa fa-fire-extinguisher fa-2x"></i>
<i class="fa fa-rocket fa-2x"></i>
<i class="fa fa-maxcdn fa-2x"></i>
<i class="fa fa-chevron-circle-left fa-2x"></i>
<i class="fa fa-chevron-circle-right fa-2x"></i>
<i class="fa fa-chevron-circle-up fa-2x"></i>
<i class="fa fa-chevron-circle-down fa-2x"></i>
<i class="fa fa-html5 fa-2x"></i>
<i class="fa fa-css3 fa-2x"></i>
<i class="fa fa-anchor fa-2x"></i>
<i class="fa fa-unlock-alt fa-2x"></i>
<i class="fa fa-bullseye fa-2x"></i>
<i class="fa fa-ellipsis-h fa-2x"></i>
<i class="fa fa-ellipsis-v fa-2x"></i>
<i class="fa fa-rss-square fa-2x"></i>
<i class="fa fa-play-circle fa-2x"></i>
<i class="fa fa-ticket fa-2x"></i>
<i class="fa fa-minus-square fa-2x"></i>
<i class="fa fa-minus-square-o fa-2x"></i>
<i class="fa fa-level-up fa-2x"></i>
<i class="fa fa-level-down fa-2x"></i>
<i class="fa fa-check-square fa-2x"></i>
<i class="fa fa-pencil-square fa-2x"></i>
<i class="fa fa-external-link-square fa-2x"></i>
<i class="fa fa-share-square fa-2x"></i>
<i class="fa fa-compass fa-2x"></i>
<i class="fa fa-toggle-down fa-2x"></i>
<i class="fa fa-caret-square-o-down fa-2x"></i>
<i class="fa fa-toggle-up fa-2x"></i>
<i class="fa fa-caret-square-o-up fa-2x"></i>
<i class="fa fa-toggle-right fa-2x"></i>
<i class="fa fa-caret-square-o-right fa-2x"></i>
<i class="fa fa-euro fa-2x"></i>
<i class="fa fa-eur fa-2x"></i>
<i class="fa fa-gbp fa-2x"></i>
<i class="fa fa-dollar fa-2x"></i>
<i class="fa fa-usd fa-2x"></i>
<i class="fa fa-rupee fa-2x"></i>
<i class="fa fa-inr fa-2x"></i>
<i class="fa fa-cny fa-2x"></i>
<i class="fa fa-rmb fa-2x"></i>
<i class="fa fa-yen fa-2x"></i>
<i class="fa fa-jpy fa-2x"></i>
<i class="fa fa-ruble fa-2x"></i>
<i class="fa fa-rouble fa-2x"></i>
<i class="fa fa-rub fa-2x"></i>
<i class="fa fa-won fa-2x"></i>
<i class="fa fa-krw fa-2x"></i>
<i class="fa fa-bitcoin fa-2x"></i>
<i class="fa fa-btc fa-2x"></i>
<i class="fa fa-file fa-2x"></i>
<i class="fa fa-file-text fa-2x"></i>
<i class="fa fa-sort-alpha-asc fa-2x"></i>
<i class="fa fa-sort-alpha-desc fa-2x"></i>
<i class="fa fa-sort-amount-asc fa-2x"></i>
<i class="fa fa-sort-amount-desc fa-2x"></i>
<i class="fa fa-sort-numeric-asc fa-2x"></i>
<i class="fa fa-sort-numeric-desc fa-2x"></i>
<i class="fa fa-thumbs-up fa-2x"></i>
<i class="fa fa-thumbs-down fa-2x"></i>
<i class="fa fa-youtube-square fa-2x"></i>
<i class="fa fa-youtube fa-2x"></i>
<i class="fa fa-xing fa-2x"></i>
<i class="fa fa-xing-square fa-2x"></i>
<i class="fa fa-youtube-play fa-2x"></i>
<i class="fa fa-dropbox fa-2x"></i>
<i class="fa fa-stack-overflow fa-2x"></i>
<i class="fa fa-instagram fa-2x"></i>
<i class="fa fa-flickr fa-2x"></i>
<i class="fa fa-adn fa-2x"></i>
<i class="fa fa-bitbucket fa-2x"></i>
<i class="fa fa-bitbucket-square fa-2x"></i>
<i class="fa fa-tumblr fa-2x"></i>
<i class="fa fa-long-arrow-down fa-2x"></i>
<i class="fa fa-long-arrow-up fa-2x"></i>
<i class="fa fa-long-arrow-left fa-2x"></i>
<i class="fa fa-long-arrow-right fa-2x"></i>
<i class="fa fa-apple fa-2x"></i>
<i class="fa fa-windows fa-2x"></i>
<i class="fa fa-android fa-2x"></i>
<i class="fa fa-linux fa-2x"></i>
<i class="fa fa-dribbble fa-2x"></i>
<i class="fa fa-skype fa-2x"></i>
<i class="fa fa-foursquare fa-2x"></i>
<i class="fa fa-trello fa-2x"></i>
<i class="fa fa-female fa-2x"></i>
<i class="fa fa-male fa-2x"></i>
<i class="fa fa-gittip fa-2x"></i>
<i class="fa fa-gratipay fa-2x"></i>
<i class="fa fa-sun-o fa-2x"></i>
<i class="fa fa-moon-o fa-2x"></i>
<i class="fa fa-archive fa-2x"></i>
<i class="fa fa-bug fa-2x"></i>
<i class="fa fa-vk fa-2x"></i>
<i class="fa fa-weibo fa-2x"></i>
<i class="fa fa-renren fa-2x"></i>
<i class="fa fa-pagelines fa-2x"></i>
<i class="fa fa-stack-exchange fa-2x"></i>
<i class="fa fa-arrow-circle-o-right fa-2x"></i>
<i class="fa fa-arrow-circle-o-left fa-2x"></i>
<i class="fa fa-toggle-left fa-2x"></i>
<i class="fa fa-caret-square-o-left fa-2x"></i>
<i class="fa fa-dot-circle-o fa-2x"></i>
<i class="fa fa-tumblr-square fa-2x"></i>
<i class="fa fa-wheelchair fa-2x"></i>
<i class="fa fa-vimeo-square fa-2x"></i>
<i class="fa fa-turkish-lira fa-2x"></i>
<i class="fa fa-try fa-2x"></i>
<i class="fa fa-plus-square-o fa-2x"></i>
<i class="fa fa-space-shuttle fa-2x"></i>
<i class="fa fa-slack fa-2x"></i>
<i class="fa fa-envelope-square fa-2x"></i>
<i class="fa fa-wordpress fa-2x"></i>
<i class="fa fa-openid fa-2x"></i>
<i class="fa fa-institution fa-2x"></i>
<i class="fa fa-bank fa-2x"></i>
<i class="fa fa-university fa-2x"></i>
<i class="fa fa-mortar-board fa-2x"></i>
<i class="fa fa-graduation-cap fa-2x"></i>
<i class="fa fa-yahoo fa-2x"></i>
<i class="fa fa-google fa-2x"></i>
<i class="fa fa-reddit fa-2x"></i>
<i class="fa fa-reddit-square fa-2x"></i>
<i class="fa fa-stumbleupon-circle fa-2x"></i>
<i class="fa fa-stumbleupon fa-2x"></i>
<i class="fa fa-delicious fa-2x"></i>
<i class="fa fa-digg fa-2x"></i>
<i class="fa fa-pied-piper fa-2x"></i>
<i class="fa fa-pied-piper-alt fa-2x"></i>
<i class="fa fa-drupal fa-2x"></i>
<i class="fa fa-joomla fa-2x"></i>
<i class="fa fa-language fa-2x"></i>
<i class="fa fa-fax fa-2x"></i>
<i class="fa fa-building fa-2x"></i>
<i class="fa fa-child fa-2x"></i>
<i class="fa fa-paw fa-2x"></i>
<i class="fa fa-spoon fa-2x"></i>
<i class="fa fa-cube fa-2x"></i>
<i class="fa fa-cubes fa-2x"></i>
<i class="fa fa-behance fa-2x"></i>
<i class="fa fa-behance-square fa-2x"></i>
<i class="fa fa-steam fa-2x"></i>
<i class="fa fa-steam-square fa-2x"></i>
<i class="fa fa-recycle fa-2x"></i>
<i class="fa fa-automobile fa-2x"></i>
<i class="fa fa-car fa-2x"></i>
<i class="fa fa-cab fa-2x"></i>
<i class="fa fa-taxi fa-2x"></i>
<i class="fa fa-tree fa-2x"></i>
<i class="fa fa-spotify fa-2x"></i>
<i class="fa fa-deviantart fa-2x"></i>
<i class="fa fa-soundcloud fa-2x"></i>
<i class="fa fa-database fa-2x"></i>
<i class="fa fa-file-pdf-o fa-2x"></i>
<i class="fa fa-file-word-o fa-2x"></i>
<i class="fa fa-file-excel-o fa-2x"></i>
<i class="fa fa-file-powerpoint-o fa-2x"></i>
<i class="fa fa-file-photo-o fa-2x"></i>
<i class="fa fa-file-picture-o fa-2x"></i>
<i class="fa fa-file-image-o fa-2x"></i>
<i class="fa fa-file-zip-o fa-2x"></i>
<i class="fa fa-file-archive-o fa-2x"></i>
<i class="fa fa-file-sound-o fa-2x"></i>
<i class="fa fa-file-audio-o fa-2x"></i>
<i class="fa fa-file-movie-o fa-2x"></i>
<i class="fa fa-file-video-o fa-2x"></i>



	<!--  -->
	
	

    


<div class="jumbotron jumbotron-ad hidden-print">
  <div class="container">
    
    <h1><i class="fa fa-magic" aria-hidden="true"></i>&nbsp; Examples</h1>
    <p>Lots of easy ways to use Font Awesome</p>
    
  </div>
</div>

<div id="social-buttons" class="hidden-print">
  <div class="container">
    <form id="newsletter" method="post" action="https://formkeep.com/f/ba101f5b69f0">
  <div class="row">
    <div class="col-md-4 col-md-offset-2 col-sm-6">
      <div class="form-group margin-bottom">
        <input name="email" type="email" class="form-control" id="email" placeholder="Enter your email address&hellip;" required>
      </div>
    </div>
    <div class="col-md-4 col-sm-6">
      <button type="submit" class="btn btn-success btn-block margin-bottom-lg"
          data-toggle="popover" data-trigger="hover" data-placement="top" title="Stay up to date with the Awesome"
          data-content="We'll send you updates on new Font Awesome releases, icons, and other stuff that we're working on. We won't spam you. Scout's honor.">
        Subscribe to Font Awesome Updates&nbsp;&nbsp;<i class="fa fa-envelope" aria-hidden="true"></i>
      </button>
    </div>
  </div>
</form>

    <ul class="list-inline">
      <li>
        <iframe class="github-btn" src="https://ghbtns.com/github-btn.html?user=FortAwesome&repo=Font-Awesome&type=watch&count=true" allowtransparency="true" frameborder="0" scrolling="0" width="100px" height="20px"></iframe>
      </li>
      <li>
        <iframe class="github-btn" src="https://ghbtns.com/github-btn.html?user=FortAwesome&repo=Font-Awesome&type=fork&count=true" allowtransparency="true" frameborder="0" scrolling="0" width="102px" height="20px"></iframe>
      </li>
      <li class="follow-btn">
        <a href="https://twitter.com/fontawesome" class="twitter-follow-button" data-link-color="#000000" data-show-count="true">Follow @fontawesome</a>
      </li>
      <li class="tweet-btn hidden-phone">
        <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://fontawesome.io" data-text="Font Awesome, the iconic font and CSS framework" data-counturl="http://fortawesome.github.com/Font-Awesome/" data-count="horizontal" data-via="fontawesome" data-related="davegandy:Creator of Font Awesome">Tweet</a>
      </li>
    </ul>
  </div>
</div>


<div class="container">
  
  <section class="hidden-print">
  <div class="stripe-ad">
    <script async type="text/javascript" src="//cdn.carbonads.com/carbon.js?zoneid=1673&serve=C6AILKT&placement=fontawesome" id="_carbonads_js"></script>

    
  <p class="lead">
    After you <a href="../get-started/">get up and running</a>, you can place Font Awesome icons just about
    anywhere with the <code>&lt;i&gt;</code> tag.
    Some examples appreciatively re-used from the <a href="http://getbootstrap.com">Bootstrap documentation</a>.
  </p>
  
  </div>
</section>


  <div class="alert alert-success gg">
    <div class="gg-col min-width">
      <i class="fa fa-universal-access fa-2x" aria-hidden"true"></i>
    </div>
    <div class="gg-col padding-left">
      <p class="margin-bottom-none">The following examples are kept simple and assume use of <a href="../get-started/get-started-cdn">Font Awesome CDN</a>, which provides auto-accessibility support. If you are not using the Font Awesome CDN, please see the <a href="../examples/#accessible">manual accessibility examples</a> and read more about <a href="../accessibility">making your icons more awesome for all users</a></p>
    </div>
  </div>

  <section id="basic">
  <h2 class="page-header">
    Basic Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/core.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_core.scss" class="text-muted">View SASS</a>
    </div>
  </h2>

  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p>
        <i class="fa fa-camera-retro"></i>
        <span class="sr-only">Example: basic icon</span>
        fa-camera-retro
      </p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        You can place Font Awesome icons just about anywhere using the CSS Prefix <code>fa</code> and the icon's
        name. Font Awesome is designed to be used with inline elements (we like the <code>&lt;i&gt;</code> tag for
        brevity, but using a <code>&lt;span&gt;</code> is more semantically correct).
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera-retro&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-camera-retro
</code></pre></div>
      <div class="alert alert-success">
        <ul class="fa-ul">
          <li>
            <i class="fa fa-info-circle fa-lg fa-li" aria-hidden="true"></i>
            <strong class="sr-only">Example: basic icon</strong>
            If you change the font-size of the icon's container, the icon gets bigger. Same things goes for color,
            drop shadow, and anything else that gets inherited using CSS.
          </li>
        </ul>
      </div>
    </div>
  </div>
</section>

  <section id="larger">
  <h2 class="page-header">
    Larger Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/larger.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_larger.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p><i class="fa fa-camera-retro fa-lg" aria-hidden="true"></i> fa-lg</p>
      <p><i class="fa fa-camera-retro fa-2x" aria-hidden="true"></i> fa-2x</p>
      <p><i class="fa fa-camera-retro fa-3x" aria-hidden="true"></i> fa-3x</p>
      <p><i class="fa fa-camera-retro fa-4x" aria-hidden="true"></i> fa-4x</p>
      <p><i class="fa fa-camera-retro fa-5x" aria-hidden="true"></i> fa-5x</p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        To increase icon sizes relative to their container, use the <code>fa-lg</code> (33% increase), <code>fa-2x</code>,
        <code>fa-3x</code>, <code>fa-4x</code>, or <code>fa-5x</code> classes.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera-retro fa-lg&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-lg
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera-retro fa-2x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-2x
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera-retro fa-3x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-3x
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera-retro fa-4x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-4x
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera-retro fa-5x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-5x
</code></pre></div>
      <div class="alert alert-success">
        <ul class="fa-ul">
          <li>
            <i class="fa fa-exclamation-triangle fa-li fa-lg"></i>
            If your icons are getting chopped off on top and bottom, make sure you have
            sufficient line-height.
          </li>
        </ul>
      </div>
    </div>
  </div>
</section>

  <section id="fixed-width">
  <h2 class="page-header">
    Fixed Width Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/fixed-width.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_fixed-width.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <div class="list-group">
        <a class="list-group-item" href="#"><i class="fa fa-home fa-fw" aria-hidden="true"></i>&nbsp; Home</a>
        <a class="list-group-item" href="#"><i class="fa fa-book fa-fw" aria-hidden="true"></i>&nbsp; Library</a>
        <a class="list-group-item" href="#"><i class="fa fa-pencil fa-fw" aria-hidden="true"></i>&nbsp; Applications</a>
        <a class="list-group-item" href="#"><i class="fa fa-cog fa-fw" aria-hidden="true"></i>&nbsp; Settings</a>
      </div>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        Use <code>fa-fw</code> to set icons at a fixed width. Great to use when different icon widths throw off alignment.
        Especially useful in things like nav lists &amp; list groups.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;list-group&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;list-group-item&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-home fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span><span class="ni">&amp;nbsp;</span> Home<span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;list-group-item&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-book fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span><span class="ni">&amp;nbsp;</span> Library<span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;list-group-item&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-pencil fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span><span class="ni">&amp;nbsp;</span> Applications<span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;list-group-item&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-cog fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span><span class="ni">&amp;nbsp;</span> Settings<span class="nt">&lt;/a&gt;</span>
<span class="nt">&lt;/div&gt;</span>
</code></pre></div>
    </div>
  </div>
</section>

  <section id="list">
  <h2 class="page-header">
    List Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/list.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_list.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <ul class="fa-ul">
        <li><i class="fa-li fa fa-check-square"></i>List icons</li>
        <li><i class="fa-li fa fa-check-square"></i>can be used</li>
        <li><i class="fa-li fa fa-spinner fa-spin"></i>as bullets</li>
        <li><i class="fa-li fa fa-square"></i>in lists</li>
      </ul>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>Use <code>fa-ul</code> and <code>fa-li</code> to easily replace default bullets in unordered lists.</p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;ul</span> <span class="na">class=</span><span class="s">&quot;fa-ul&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;li&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa-li fa fa-check-square&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>List icons<span class="nt">&lt;/li&gt;</span>
  <span class="nt">&lt;li&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa-li fa fa-check-square&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>can be used<span class="nt">&lt;/li&gt;</span>
  <span class="nt">&lt;li&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa-li fa fa-spinner fa-spin&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>as bullets<span class="nt">&lt;/li&gt;</span>
  <span class="nt">&lt;li&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa-li fa fa-square&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>in lists<span class="nt">&lt;/li&gt;</span>
<span class="nt">&lt;/ul&gt;</span>
</code></pre></div>
    </div>
  </div>
</section>

  <section id="bordered-pulled">
  <h2 class="page-header">
    Bordered &amp; Pulled Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/bordered-pulled.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_bordered-pulled.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p>
        <i class="fa fa-quote-left fa-3x fa-pull-left fa-border" aria-hidden="true"></i>
        &hellip;tomorrow we will run faster, stretch out our arms farther&hellip; And then one fine morning&mdash;
        So we beat on, boats against the current, borne back ceaselessly into the past.
      </p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        Use <code>fa-border</code> and <code>fa-pull-right</code> or <code>fa-pull-left</code> for easy pull quotes or
        article icons.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-quote-left fa-3x fa-pull-left fa-border&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
...tomorrow we will run faster, stretch out our arms farther...
And then one fine morning<span class="ni">&amp;mdash;</span> So we beat on, boats against the
current, borne back ceaselessly into the past.
</code></pre></div>
    </div>
  </div>
</section>

  <section id="animated">
  <h2 class="page-header">
    Animated Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/animated.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_animated.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p>
        <i class="fa fa-spinner fa-spin fa-3x fa-fw margin-bottom"></i>
        <span class="sr-only">Loading example (with fa-spinner icon)</span>

        <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw margin-bottom"></i>
        <span class="sr-only">Loading (with fa-circle-o-notch icon)</span>

        <i class="fa fa-refresh fa-spin fa-3x fa-fw margin-bottom"></i>
        <span class="sr-only">Loading example (with fa-refresh icon)</span>

        <i class="fa fa-cog fa-spin fa-3x fa-fw margin-bottom"></i>
        <span class="sr-only">Loading example (with fa-cog icon)</span>

        <i class="fa fa-spinner fa-pulse fa-3x fa-fw margin-bottom"></i>
        <span class="sr-only">Loading example (with fa-spinner icon)</span>
      </p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        Use the <code>fa-spin</code> class to get any icon to rotate, and use <code>fa-pulse</code> to have it rotate
        with 8 steps. Works well with <code>fa-spinner</code>, <code>fa-refresh</code>, and <code>fa-cog</code>.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-spinner fa-spin fa-3x fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Loading...<span class="nt">&lt;/span&gt;</span>

<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-circle-o-notch fa-spin fa-3x fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Loading...<span class="nt">&lt;/span&gt;</span>

<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-refresh fa-spin fa-3x fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Loading...<span class="nt">&lt;/span&gt;</span>

<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-cog fa-spin fa-3x fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Loading...<span class="nt">&lt;/span&gt;</span>

<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-spinner fa-pulse fa-3x fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Loading...<span class="nt">&lt;/span&gt;</span>
</code></pre></div>
      <p class="alert alert-success">
        <i class="fa fa-exclamation-triangle fa-lg" aria-hidden="true"></i><strong class="sr-only">Note:</strong>
        Some browsers on some platforms have issues with animated icons resulting in a jittery wobbling effect. See
        <a href="https://github.com/FortAwesome/Font-Awesome/issues/671" class="alert-link" target="_blank">issue #671</a>
        for examples and possible workarounds.
      </p>
      <p class="alert alert-success">
        <i class="fa fa-info-circle fa-lg" aria-hidden="true"></i><strong class="sr-only">Note:</strong> CSS3 animations aren't supported in IE8 - IE9.
      </p>
    </div>
  </div>
</section>

  <section id="rotated-flipped">
  <h2 class="page-header">
    Rotated &amp; Flipped
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/rotated-flipped.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_rotated-flipped.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p style="font-size: 18px;">
        <i class="fa fa-shield" aria-hidden="true"></i>&nbsp; normal<br>
        <i class="fa fa-shield fa-rotate-90" aria-hidden="true"></i>&nbsp; fa-rotate-90<br>
        <i class="fa fa-shield fa-rotate-180" aria-hidden="true"></i>&nbsp; fa-rotate-180<br>
        <i class="fa fa-shield fa-rotate-270" aria-hidden="true"></i>&nbsp; fa-rotate-270<br>
        <i class="fa fa-shield fa-flip-horizontal" aria-hidden="true"></i>&nbsp; fa-flip-horizontal<br>
        <i class="fa fa-shield fa-flip-vertical" aria-hidden="true"></i>&nbsp; fa-flip-vertical
      </p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        To arbitrarily rotate and flip icons, use the <code>fa-rotate-*</code> and <code>fa-flip-*</code> classes.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shield&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> normal<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shield fa-rotate-90&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-rotate-90<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shield fa-rotate-180&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-rotate-180<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shield fa-rotate-270&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-rotate-270<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shield fa-flip-horizontal&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-flip-horizontal<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shield fa-flip-vertical&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> fa-flip-vertical
</code></pre></div>
    </div>
  </div>
</section>

  <section id="stacked">
  <h2 class="page-header">
    Stacked Icons
    <div class="pull-right text-default margin-top padding-top-sm hidden-xs">
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/less/stacked.less" class="text-muted padding-right">View LESS</a>
      <a href="https://github.com/FortAwesome/Font-Awesome/blob/v4.7.0/scss/_stacked.scss" class="text-muted">View SASS</a>
    </div>
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <div class="margin-bottom">
        <span class="fa-stack fa-lg" aria-hidden="true">
          <i class="fa fa-square-o fa-stack-2x"></i>
          <i class="fa fa-twitter fa-stack-1x"></i>
        </span>
        fa-twitter on fa-square-o<br>
        <span class="fa-stack fa-lg" aria-hidden="true">
          <i class="fa fa-circle fa-stack-2x"></i>
          <i class="fa fa-flag fa-stack-1x fa-inverse"></i>
        </span>
        fa-flag on fa-circle<br>
        <span class="fa-stack fa-lg" aria-hidden="true">
          <i class="fa fa-square fa-stack-2x"></i>
          <i class="fa fa-terminal fa-stack-1x fa-inverse"></i>
        </span>
        fa-terminal on fa-square<br>
        <span class="fa-stack fa-lg" aria-hidden="true">
          <i class="fa fa-camera fa-stack-1x"></i>
          <i class="fa fa-ban fa-stack-2x text-danger"></i>
        </span>
        fa-ban on fa-camera
      </div>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        To stack multiple icons, use the <code>fa-stack</code> class on the parent, the <code>fa-stack-1x</code>
        for the regularly sized icon, and <code>fa-stack-2x</code> for the larger icon. <code>fa-inverse</code>
        can be used as an alternative icon color. You can even throw <a href="#larger">larger icon</a> classes on the parent
        to get further control of sizing.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;fa-stack fa-lg&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-square-o fa-stack-2x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-twitter fa-stack-1x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/span&gt;</span>
fa-twitter on fa-square-o<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;fa-stack fa-lg&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-circle fa-stack-2x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-flag fa-stack-1x fa-inverse&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/span&gt;</span>
fa-flag on fa-circle<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;fa-stack fa-lg&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-square fa-stack-2x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-terminal fa-stack-1x fa-inverse&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/span&gt;</span>
fa-terminal on fa-square<span class="nt">&lt;br&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;fa-stack fa-lg&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-camera fa-stack-1x&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-ban fa-stack-2x text-danger&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/span&gt;</span>
fa-ban on fa-camera
</code></pre></div>
    </div>
  </div>
</section>

  <section id="bootstrap">
  <h2 class="page-header">Bootstrap 3 Examples</h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p>
        <a class="btn btn-danger" href="#">
          <i class="fa fa-trash-o fa-lg"></i> Delete</a>
        <a class="btn btn-default btn-sm" href="#">
          <i class="fa fa-cog"></i> Settings</a>
      </p>
      <p>
        <a class="btn btn-lg btn-success" href="#">
          <i class="fa fa-flag fa-2x pull-left"></i> Font Awesome<br>Version 4.7.0</a>
      </p>
      <div class="margin-bottom">
        <div class="btn-group">
          <a class="btn btn-default" href="#">
            <i class="fa fa-align-left" title="Align Left"></i>
          </a>
          <a class="btn btn-default" href="#">
            <i class="fa fa-align-center" title="Align Center"></i>
          </a>
          <a class="btn btn-default" href="#">
            <i class="fa fa-align-right" title="Align Right"></i>
          </a>
          <a class="btn btn-default" href="#">
            <i class="fa fa-align-justify" title="Align Justify"></i>
          </a>
        </div>
      </div>
      <div class="margin-bottom">
        <div class="input-group margin-bottom-sm">
          <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw"></i></span>
          <input class="form-control" type="text" placeholder="Email address">
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
          <input class="form-control" type="password" placeholder="Password">
        </div>
      </div>
      <div class="margin-bottom">
        <div class="btn-group open">
          <a class="btn btn-primary" href="#"><i class="fa fa-user fa-fw"></i> User</a>
          <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="fa fa-caret-down" title="Toggle dropdown menu"></span>
          </a>
          <ul class="dropdown-menu">
            <li><a href="#"><i class="fa fa-pencil fa-fw"></i> Edit</a></li>
            <li><a href="#"><i class="fa fa-trash-o fa-fw"></i> Delete</a></li>
            <li><a href="#"><i class="fa fa-ban fa-fw"></i> Ban</a></li>
            <li class="divider"></li>
            <li><a href="#"><i class="fa fa-unlock"></i> Make admin</a></li>
          </ul>
        </div>
      </div>

    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        Font Awesome works great with the full range of Bootstrap components.
      </p>
<div class="highlight"><pre><code class="html"><span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-danger&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-trash-o fa-lg&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Delete<span class="nt">&lt;/a&gt;</span>
<span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-default btn-sm&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-cog&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Settings<span class="nt">&lt;/a&gt;</span>

<span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-lg btn-success&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-flag fa-2x pull-left&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Font Awesome<span class="nt">&lt;br&gt;</span>Version 4.7.0<span class="nt">&lt;/a&gt;</span>

<span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;btn-group&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-default&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
    <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-align-left&quot;</span> <span class="na">title=</span><span class="s">&quot;Align Left&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-default&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
    <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-align-center&quot;</span> <span class="na">title=</span><span class="s">&quot;Align Center&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-default&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
    <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-align-right&quot;</span> <span class="na">title=</span><span class="s">&quot;Align Right&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-default&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
    <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-align-justify&quot;</span> <span class="na">title=</span><span class="s">&quot;Align Justify&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
  <span class="nt">&lt;/a&gt;</span>
<span class="nt">&lt;/div&gt;</span>

<span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;input-group margin-bottom-sm&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;input-group-addon&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-envelope-o fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;&lt;/span&gt;</span>
  <span class="nt">&lt;input</span> <span class="na">class=</span><span class="s">&quot;form-control&quot;</span> <span class="na">type=</span><span class="s">&quot;text&quot;</span> <span class="na">placeholder=</span><span class="s">&quot;Email address&quot;</span><span class="nt">&gt;</span>
<span class="nt">&lt;/div&gt;</span>
<span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;input-group&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;input-group-addon&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-key fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;&lt;/span&gt;</span>
  <span class="nt">&lt;input</span> <span class="na">class=</span><span class="s">&quot;form-control&quot;</span> <span class="na">type=</span><span class="s">&quot;password&quot;</span> <span class="na">placeholder=</span><span class="s">&quot;Password&quot;</span><span class="nt">&gt;</span>
<span class="nt">&lt;/div&gt;</span>

<span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;btn-group open&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-primary&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-user fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> User<span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-primary dropdown-toggle&quot;</span> <span class="na">data-toggle=</span><span class="s">&quot;dropdown&quot;</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;</span>
    <span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;fa fa-caret-down&quot;</span> <span class="na">title=</span><span class="s">&quot;Toggle dropdown menu&quot;</span><span class="nt">&gt;&lt;/span&gt;</span>
  <span class="nt">&lt;/a&gt;</span>
  <span class="nt">&lt;ul</span> <span class="na">class=</span><span class="s">&quot;dropdown-menu&quot;</span><span class="nt">&gt;</span>
    <span class="nt">&lt;li&gt;&lt;a</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-pencil fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Edit<span class="nt">&lt;/a&gt;&lt;/li&gt;</span>
    <span class="nt">&lt;li&gt;&lt;a</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-trash-o fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Delete<span class="nt">&lt;/a&gt;&lt;/li&gt;</span>
    <span class="nt">&lt;li&gt;&lt;a</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-ban fa-fw&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Ban<span class="nt">&lt;/a&gt;&lt;/li&gt;</span>
    <span class="nt">&lt;li</span> <span class="na">class=</span><span class="s">&quot;divider&quot;</span><span class="nt">&gt;&lt;/li&gt;</span>
    <span class="nt">&lt;li&gt;&lt;a</span> <span class="na">href=</span><span class="s">&quot;#&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-unlock&quot;</span><span class="nt">&gt;&lt;/i&gt;</span> Make admin<span class="nt">&lt;/a&gt;&lt;/li&gt;</span>
  <span class="nt">&lt;/ul&gt;</span>
<span class="nt">&lt;/div&gt;</span>
</code></pre></div>
    </div>
  </div>
</section>

  <section id="custom">
  <h2 class="page-header">Custom CSS</h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p>Anything you can do with CSS font styles, you can do with Font Awesome.</p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>Star Ratings (inspired by <a href="http://css-tricks.com/star-ratings/" target="_blank">CSS Tricks</a>)</p>
      <div class="well">
        <span class="rating">
          <span class="star"></span><span class="star"></span><span class="star"></span><span class="star"></span><span class="star"></span>
        </span>
      </div>
    </div>
  </div>
</section>

  <section id="accessible">
  <h2 class="page-header">
    Accessibility-Minded
  </h2>
  <div class="row">
    <div class="col-md-3 col-sm-4">
      <p>
        <a class="btn btn-default" href="path/to/settings" aria-label="Settings">
          <i class="fa fa-cog" aria-hidden="true"></i>
        </a>

        <a class="btn btn-danger" href="path/to/settings" aria-label="Delete">
          <i class="fa fa-trash-o" aria-hidden="true"></i>
        </a>

        <a class="btn btn-primary" href="#navigation-main" aria-label="Skip to main navigation">
          <i class="fa fa-bars" aria-hidden="true"></i>
        </a>
      </p>

      <p>
        <i class="fa fa-refresh fa-spin fa-3x fa-fw" aria-hidden="true"></i>
        <span class="sr-only">Refreshing...</span>

        <i class="fa fa-cog fa-spin fa-3x fa-fw" aria-hidden="true"></i>
        <span class="sr-only">Saving. Hang tight!</span>
      </p>

      <p>
        <div class="input-group margin-bottom-sm">
          <span class="input-group-addon"><i class="fa fa-envelope-o fa-fw" aria-hidden="true"></i></span>
          <input class="form-control" type="text" placeholder="Email address">
        </div>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-key fa-fw" aria-hidden="true"></i></span>
          <input class="form-control" type="password" placeholder="Password">
        </div>
      </p>

      <p>
        <a href="path/to/shopping/cart" class="btn btn-primary" aria-label="View 3 items in your shopping cart">
          <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        </a>
      </p>

      <p>
        <i class="fa fa-battery-half" aria-hidden="true"></i>
        <span class="sr-only">Battery level: 50%</span>
      </p>
    </div>
    <div class="col-md-9 col-sm-8">
      <p>
        With <a href="../accessibility/">our thoughts on icon accessibility</a> in mind, If an icon only adds some extra decoration or branding, it does not need to be announced to users as they are navigating your site or app aurally. Alternatively, if an icon conveys meaning in your content or interface, ensure that this meaning is also conveyed to assistive technologies through alternative displays or text.
      </p>

<div class="highlight"><pre><code class="html"><span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-default&quot;</span> <span class="na">href=</span><span class="s">&quot;path/to/settings&quot;</span> <span class="na">aria-label=</span><span class="s">&quot;Settings&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-cog&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/a&gt;</span>

<span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-danger&quot;</span> <span class="na">href=</span><span class="s">&quot;path/to/settings&quot;</span> <span class="na">aria-label=</span><span class="s">&quot;Delete&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-trash-o&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/a&gt;</span>

<span class="nt">&lt;a</span> <span class="na">class=</span><span class="s">&quot;btn btn-primary&quot;</span> <span class="na">href=</span><span class="s">&quot;#navigation-main&quot;</span> <span class="na">aria-label=</span><span class="s">&quot;Skip to main navigation&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-bars&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/a&gt;</span>
</code></pre></div>

<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-refresh fa-spin fa-3x fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Refreshing...<span class="nt">&lt;/span&gt;</span>

<span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-cog fa-spin fa-3x fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Saving. Hang tight!<span class="nt">&lt;/span&gt;</span>
</code></pre></div>

<div class="highlight"><pre><code class="html"><span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;input-group margin-bottom-sm&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;input-group-addon&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-envelope-o fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;&lt;/span&gt;</span>
  <span class="nt">&lt;input</span> <span class="na">class=</span><span class="s">&quot;form-control&quot;</span> <span class="na">type=</span><span class="s">&quot;text&quot;</span> <span class="na">placeholder=</span><span class="s">&quot;Email address&quot;</span><span class="nt">&gt;</span>
<span class="nt">&lt;/div&gt;</span>
<span class="nt">&lt;div</span> <span class="na">class=</span><span class="s">&quot;input-group&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;input-group-addon&quot;</span><span class="nt">&gt;&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-key fa-fw&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;&lt;/span&gt;</span>
  <span class="nt">&lt;input</span> <span class="na">class=</span><span class="s">&quot;form-control&quot;</span> <span class="na">type=</span><span class="s">&quot;password&quot;</span> <span class="na">placeholder=</span><span class="s">&quot;Password&quot;</span><span class="nt">&gt;</span>
<span class="nt">&lt;/div&gt;</span>
</code></pre></div>

<div class="highlight"><pre><code class="html"><span class="nt">&lt;a</span> <span class="na">href=</span><span class="s">&quot;path/to/shopping/cart&quot;</span> <span class="na">class=</span><span class="s">&quot;btn btn-primary&quot;</span> <span class="na">aria-label=</span><span class="s">&quot;View 3 items in your shopping cart&quot;</span><span class="nt">&gt;</span>
  <span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-shopping-cart&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;/a&gt;</span>
</code></pre></div>

<div class="highlight"><pre><code class="html"><span class="nt">&lt;i</span> <span class="na">class=</span><span class="s">&quot;fa fa-battery-half&quot;</span> <span class="na">aria-hidden=</span><span class="s">&quot;true&quot;</span><span class="nt">&gt;&lt;/i&gt;</span>
<span class="nt">&lt;span</span> <span class="na">class=</span><span class="s">&quot;sr-only&quot;</span><span class="nt">&gt;</span>Battery level: 50%<span class="nt">&lt;/span&gt;</span>
</code></pre></div>
    </div>
  </div>
</section>

</div>

  
  
</div>