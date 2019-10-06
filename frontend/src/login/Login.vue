<template>
<div><nbLOGIN></nbLOGIN>
<div class="from-login">
  <div class="card mx-xl-5">
    <div class="card-body">
            <p class="h4 text-center py-4">เลือกระบบที่ต้องการบันทึก</p>
            <label class="grey-text font-weight-light">เลือกระบบ :</label>
            <select v-model="system"  class="browser-default custom-select" >
                <option  value="null" disabled selected>เลือกระบบ</option>
                <option  value="3" >SupplyProcurementSystem</option>  <!-- Chatdanai Phakaket-->
                <option  value="4" >WorksheetSystem</option>  <!-- Pattarasit Lomthaisong -->
                <option  value="5" >UsingAmbulanceSystem</option>  <!-- Pairat Tonkean -->
                <option  value="1" >AmbulanceExpenseSystem</option>  <!-- Damrong Khruaesri -->
                <option  value="6" >RepairRecordSystem</option>  <!-- Chiwin Siriphan -->
                <option  value="2" >AmbulancePurchaseSystem</option>  <!-- Natthawat Narin -->
            </select>
            <br>
            <br>
            <label class="grey-text font-weight-light">กรอกรหัส :</label>
            <input v-model="password" type="password" class="form-control" style="text-align: center">

            <div class="text-center py-4 mt-3">
                <button class="btn btn-indigo" style="width:40%" @click="ChkLogin">เข้าสู่ระบบ</button>
            </div>      
    </div>
  </div>
</div>
</div>
</template>

<script>
import axi from "../http-common";
import nbLOGIN from '@/navbar/nbLOGIN';
export default {
  name: 'Login',
  components: {
    nbLOGIN
  },
  data () {
    return {
      system: 'null',
      password: ''
    };
  },
  methods:{
    ChkLogin() {
      axi
        .post("/chkLogin/" + this.system +"/" + this.password)
        .then(response => {
          
          if(response.data.id == null){
            alert("password ไม่ถูกต้อง : ");
          }else if(response.data.id == '3'){
            alert("เข้าสู่ระบบในฐานะ : " +response.data.position.position);
            location.href="http://localhost:8080/#/SupplyProcurement"
          }else if(response.data.id == '4'){
            alert("เข้าสู่ระบบในฐานะ : " +response.data.position.position);
            location.href="http://localhost:8080/#/Worksheet"
          }else if(response.data.id == '5'){
            alert("เข้าสู่ระบบในฐานะ : " +response.data.position.position);
            location.href="http://localhost:8080/#/UsingAmbulance"
          }else if(response.data.id == '1'){
            alert("เข้าสู่ระบบในฐานะ :" +response.data.position.position);
            location.href="http://localhost:8080/#/AmbulanceExpense"
          }else if(response.data.id == '6'){
            alert("เข้าสู่ระบบในฐานะ : " +response.data.position.position);
            location.href="http://localhost:8080/#/RepairRecord"
          }else if(response.data.id == '2'){
            alert("เข้าสู่ระบบในฐานะ : " +response.data.position.position);
            location.href="http://localhost:8080/#/AmbulancePurchase"
          }

        })
    }
  }
};    

</script>

<style>
.from-login {
  width: 500px;
  height: 500px;
  margin: 200px auto;
}
</style>
