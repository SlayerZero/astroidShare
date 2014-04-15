<!DOCTYPE  html>
<html>
<head>
   <meta charset="UTF-8">
   <title>.::Galeria::.</title>
   <link rel="stylesheet" type="text/css" href="galeria.css" />
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src='https://raw.github.com/markdalgleish/stellar.js/master/jquery.stellar.js'></script>
<body>
   <section id="main_container">
      <header class="header">
         <h1 id="home">.::ASTEROIDES::.</h1>
         <nav class="top_menu">
           <ul>
           <li><a href="index.html">Home</a></li>
            </ul>
         </nav>
      </header>
      <section class="content">
         <article>
            <p>Revisa las fotos que tenemos para ti</p>
<div id="gallery">
  <?php
$total = count(glob("a/{*.jpg,*.gif,*.png}",GLOB_BRACE));
//echo "total".$total;
$i = 0;
for($i = 1; $i <= $total ; $i++){
  echo "<img alt='' src='a/i".$i.".jpg' />";
  }
  ?>
</div>
         </article>
      </section>
      <footer class="footer">
         <span class="footer_msg"> Proyecto Astroid</span>
      </footer>
   </section>
</body>  
</html>