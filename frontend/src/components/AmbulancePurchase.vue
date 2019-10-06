<template>
<div><nbH_ER></nbH_ER>
  <div class="from-AmbulancePurchase">
    <div class="card">
      <h2 class="card-header info-color white-text text-center py-6">
        <strong>บันทึกการจัดซื้อรถพยาบาล</strong>
      </h2>
      <div class="card-body px-lg-9 pt-1">
        <form class="text-center" >
          <br />
          <div class="form-row">
            <h4>
              <b>ผู้บันทึกข้อมูล</b>
            </h4>
          </div>
          <div class="form-row">
            <div class="col-6">
              <div class="form-group">
                <label>ชื่อพนักงาน: </label>
                <select v-model="AmbulancePurchase.employee_id" name="employee_id"  class="browser-default custom-select custom-select-sm">
                      <option value="default" disabled >เลือกชื่อพนักงาน</option>
                      <option  v-for="emp in employee"  :key="emp.id" :value="emp.id" > {{ emp.name }} </option>
                </select>
              </div>
            </div>

          </div>
          <div class="form-row">
            <h4>
              <b>ข้อมูลรถที่จัดซื้อ</b>
            </h4>
          </div>
          <div class="form-row">
            <div class="col">
              <div class="form-group">
                <label>บริษัทของรถพยาบาลที่จัดชื้อ: </label>
                <select v-model="company_id" class="browser-default custom-select custom-select-sm"   @change="setAm_Product">
                      <option value="default" disabled >เลือกบริษัทรถพยาบาลที่จัดชื้อ</option>
                      <option v-for="comp in company"  :key="comp.id" :value="comp.id" > {{ comp.name }} </option>
                </select>
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                 <label>รุ่นของรถพยาบาลที่จัดซื้อ: </label>
                 <select v-model="AmbulancePurchase.am_product_id" name="AmbulancePurchase.am_company_id"  @change="setPrice()" class="browser-default custom-select custom-select-sm">
                      <option value="default" disabled >เลือกรุ่นของรถพยาบาลที่จัดซื้อ</option>
                      <option v-for="amc in am_product"  :key="amc.id" :value="amc.id" > {{ amc.name }} </option>
                </select>
              </div>
            </div>
          </div>
          <div class="form-row">
            <div class="col">
              <div class="form-group">
                <label>วันที่จัดซื้อรถพยาบาล: </label>
                <date-picker v-model="tmpDate" width="467" lang="en" type="date"  format="YYYY-MM-DD"  placeholder="เลือกวันที่จัดซื้อ" @change="setDate" ></date-picker>
              </div>
            </div>
            <div class="col">
              <div class="form-group">
                <label>เลขตัวถังรถพยาบาล 17 หลัก: <span class="text-right" style="margin-left: 250px;">{{AmbulancePurchase.ap_vin.length}}</span> </label>
                <input v-model="AmbulancePurchase.ap_vin" name="AmbulancePurchase.pvin" type="text" placeholder="เช่น JR099874537534523" class="form-control form-control-sm"/>

              </div>
            </div>
          </div>
          <div class="form-row">
            <div class="col">
              <div class="form-group">
              <label>รหัสรถพยาบาลในระบบ: </label>
                <input v-model="am_code" name="am_code" type="text" disabled class="form-control form-control-sm"/>
              </div>
            </div>
            <div class="col-6">
              <div class="form-group">
                <label></label><br>
                <label >ราคารถพยาบาลที่จัดซื้อ: {{ price }}</label>
               </div>
            </div>
          </div>
          <br />
          <center>
            <div>
              <mdb-btn color="info" @click="checkData" ><h5>บันทึกข้อมูลการจัดซื้อรถพยาบาล</h5></mdb-btn>
            </div>
          </center>
          <hr />
        </form>
      </div>
    </div>
    <br />
    <br />
    <br />
  </div>
</div>
</template>

<script>
// eslint-disable-next-line
/* eslint-disable */
import DatePicker from 'vue2-datepicker';
import nbH_ER from '@/navbar/nbH_ER';
import { mdbBtn, mdbInput } from "mdbvue";
import http from "../http-common";
export default {
  name: "AmbulancePurchase",
  components: {
    DatePicker,
    mdbBtn,
    nbH_ER
  },
  data() {
    return {
      tmpDate: '',
      checkAmbulancePurchase:[],
      AmbulancePurchase:{
        ap_date:'',
        ap_vin:'',
        employee_id:'default',
        am_vehicle_id:'default',
        am_product_id:'default'
      },
      employee: [],
      employeeName: '',
      am_vehicle:[],
      am_code: '',
      am_code_length: '',
      company: [],
      company_id:'default',
      am_product: [],
      tmpAm_Product: [],
      price:'',
    };
  },
  methods:{
    setDate() {
      var tmpYear = this.tmpDate.getFullYear();
      var tmpMonth = this.tmpDate.getMonth() + 1;
      var tmpDate = this.tmpDate.getDate();
      if(tmpMonth<10)tmpMonth="0"+tmpMonth;
      if(tmpDate<10)tmpDate="0"+tmpDate;
      this.AmbulancePurchase.ap_date = tmpYear +"-"+ tmpMonth + "-"+ tmpDate;
    },
    checkData(){
      if(this.AmbulancePurchase.employee_id == 'default'){
          alert("กรุณาเลือกพนักงาน");
      }else if(this.company_id == 'default'){
          alert("กรุณาเลือกบริษัทที่จัดซื้อ");
      }else if(this.AmbulancePurchase.am_product_id == 'default'){
          alert("กรุณาเลือกรถพยาบาลที่จัดซื้อ");
      }else if(this.AmbulancePurchase.ap_date == ''){
          alert("กรุณาเลือกวันที่จัดซื้อ");
      }else if(this.AmbulancePurchase.ap_vin == ''){
          alert("กรุณากรอกเลขตัวถังรถ");
      }else if(this.AmbulancePurchase.ap_vin.length != 17){
          alert("เลขตัวถังรถต้องมี 17 หลัก");
      }else if(this.checkVin()){
        alert("ไม่สามารถใชเลขตัวถังนี้นี้ได้");
      }else if(this.am_code == ''){
          alert("กรุณากรอกรหัสรถพยาบาล");
      }else{
        this.addAm_Vehicle();

      }
    },
    //Vehicle Identification Number (VIN) 17 หลัก
    checkVin(){
      for (let elem in this.checkAmbulancePurchase) {
          if(this.checkAmbulancePurchase[elem].vin == this.AmbulancePurchase.ap_vin.trim()){
              return true;

          }
      }
      return false;
    },
    getEmployee() {
      http
        .get("/employees/")
        .then(response => {
          this.employee = response.data;
          this.$nextTick();
          console.log(this.employee);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAm_Vehicle() {
      http
        .get("/vehicles/")
        .then(response => {
          this.am_vehicle = response.data;
          this.AmbulancePurchase.am_vehicle_id = response.data.length+1;
          this.am_code = 'AM00' + Number(response.data.length+1);
          this.$nextTick();
          console.log(this.am_vehicle);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getCompany() {
      http
        .get("/company/")
        .then(response => {
          this.company = response.data;
          this.$nextTick();
          console.log(this.company);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAm_Product(){
      http
        .get("/am_product/")
        .then(response => {
          this.tmpAm_Product = response.data;
          this.$nextTick();
          console.log(this.tmpAm_Product);
        })
        .catch(e => {
          console.log(e);
        });
    },
    setAm_Product(){
      let am_product = [];
      let i = 0;
      for (let elem in this.tmpAm_Product) {
          if(this.tmpAm_Product[elem].company.id == this.company_id){
              am_product[i] = this.tmpAm_Product[elem];
              i=i+1;
          }
      }
      this.am_product = am_product;
      console.log(am_product);
    },
    setPrice(){
      for (let elem in this.am_product) {
          if(this.am_product[elem].id == this.AmbulancePurchase.am_product_id){
              this.price = this.am_product[elem].price;
          }
      }
    },
    addAm_Vehicle(){
      http
        .post("/newAm_vehicle/"+
          this.am_code
        )
        .then(response => {
          this.addAmbulancePurchase(response.data.id);
        })
        .catch(e => {

          alert("บันทึกข้อมูลไม่สำเร็จเนื่องจาก: "+e);
          console.log(e);
        });
    },
    getAmbulancePurchase() {
      http
        .get("/ambulancePurchase/")
        .then(response => {
          this.checkAmbulancePurchase = response.data;
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },
    addAmbulancePurchase(vehicle_id){
      http
        .post("/ambulancePurchase/"+
          this.AmbulancePurchase.ap_date+
          "/" +this.AmbulancePurchase.ap_vin +
          "/" +this.AmbulancePurchase.employee_id +
          "/" + vehicle_id +
          "/" +this.AmbulancePurchase.am_product_id,
          this.AmbulancePurchase
        )
        .then(response => {
          alert("บันทึกข้อมูลการจัดซื้อรถพยาบาลสำเร็จ");
          // this.$router.push("/view");
        })
        .catch(e => {
          alert("บันทึกข้อมูลไม่สำเร็จเนื่องจาก: "+e);
          console.log(e);
        });
    },
  }, // end methods
  mounted(){
    this.getAmbulancePurchase();
    this.getEmployee();
    this.getAm_Vehicle();
    this.getCompany();
    this.getAm_Product();
  }
}; // end export default
</script>
<style>
.from-AmbulancePurchase {
  width:1024px;
  height: 500px;
  margin: 100px auto;
  position: relative;
}
.form-group{
  margin-left: 20px;
  text-align: left;
}
</style>
