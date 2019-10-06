<template>
<mdb-navbar dark position="top" color="white" scrolling :scrollingOffset="20" >
      <mdb-navbar-brand to="/" waves class="font-weight-bold"><span class="grey-text" style="font-size:30px;">AMBULANCE SYSTEM</span> </mdb-navbar-brand>
</mdb-navbar>
</template>

<script>
import { mdbNavbar, mdbNavbarBrand, mdbNavbarToggler, mdbNavbarNav, mdbNavItem } from 'mdbvue';
export default {
  name: 'nbLOGIN',
  components: {
    mdbNavbar,
    mdbNavbarBrand,
    mdbNavbarToggler,
    mdbNavbarNav,
    mdbNavItem,
  }
};    

</script>
