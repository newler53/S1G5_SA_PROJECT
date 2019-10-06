<template>
<mdb-navbar dark position="top" color="white" scrolling :scrollingOffset="20" >
      <mdb-navbar-brand to="/" waves class="font-weight-bold"><span class="grey-text" style="font-size:30px;">AMBULANCE SYSTEM</span> </mdb-navbar-brand>
      <mdb-navbar-toggler>
        <mdb-navbar-nav right>
          <mdb-nav-item ><span  class="grey-text" style="font-size:25px;">USER LAVEL:  DRIVER  </span></mdb-nav-item>
          <mdb-nav-item href="http://localhost:8080/#/"><span  class="font-weight-bold d-inline p-2 bg-dark text-white" style="font-size:25px;" active>  LOG OUT </span></mdb-nav-item>
        </mdb-navbar-nav>
      </mdb-navbar-toggler>
</mdb-navbar>
</template>

<script>
import { mdbNavbar, mdbNavbarBrand, mdbNavbarToggler, mdbNavbarNav, mdbNavItem } from 'mdbvue';
export default {
  name: 'nbDRIVER',
  components: {
    mdbNavbar,
    mdbNavbarBrand,
    mdbNavbarToggler,
    mdbNavbarNav,
    mdbNavItem,
  }
};    
</script>
