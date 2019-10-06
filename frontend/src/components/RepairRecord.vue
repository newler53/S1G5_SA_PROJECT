<template>
<div><nbDRIVER></nbDRIVER> 
<div class="from-RepairRecord">
<div class="bg">
    <p class="py-5 text-center">
      <mdb-col md="8"></mdb-col>
      <mdb-col md="2">
        </mdb-col>
    </p>
  </div>
  <label></label>
    <mdb-row>
      
      <mdb-col md="2"></mdb-col>
      <mdb-col md="8">
        
        <h1><p class="text-center">ระบบบันทึกข้อมูลการซ่อมบำรุงรถพยาบาล</p></h1>
      </mdb-col>
    </mdb-row>
<mdb-row>
      <mdb-col md="3">
      </mdb-col>
      <mdb-col md="5">
          <select v-model="employee_id"  class="browser-default custom-select">
          <option value="null" disabled> เลือกชื่อผู้บันทึก </option>
          <option  v-for="emp in employee"  :key="emp.id" :value="emp.id" > 
          เจ้าหน้าที่ {{ emp.name }} ตำแหน่ง {{emp.position.position}}
          </option>
        </select>

      </mdb-col>
      <mdb-col md="3">
      </mdb-col>
</mdb-row>
<mdb-row>
      <mdb-col md="3">
      </mdb-col>
      <mdb-col md="5">
        <div>
          <label></label>
    <select v-model="am_vehicle_id"  class="browser-default custom-select">
        <option value="null" disabled> เลือกรหัสรถพยาบาล </option>
        <option  v-for="am in am_vehicle"  :key="am.id" :value="am.id" > {{ am.am_Code }} </option>
    </select>
        </div>
      </mdb-col>
        <mdb-col md="3">
      </mdb-col>
</mdb-row>
<mdb-row>
      <mdb-col md="3">
      </mdb-col>
      <mdb-col md="5">
   <div>
     <label></label>
      <select v-model="damageType_id"  class="browser-default custom-select">
        <option value="null" disabled> เลือกประเภทความเสียหายของรถพยาบาล </option>
        <option  v-for="dt in damageType"  :key="dt.id" :value="dt.id" > {{ dt.type }} </option>
      </select>
  </div>
    </mdb-col>
</mdb-row>

  <mdb-row>
      <mdb-col md="3">
      </mdb-col>
    <mdb-col md="5">
  <mdb-input label="ระยะเวลาการซ่อมบำรุง(วัน)" v-model="time" />
    </mdb-col>
    </mdb-row>
    <mdb-row>


    <mdb-col md="6">
    </mdb-col>
        <mdb-col md="3">
            <button  class="btn btn-primary btn-lg" @click="checkList">บันทึกข้อมูล</button>
        </mdb-col>
              <mdb-col md="4">
      </mdb-col>
    <mdb-col md="4">
    </mdb-col>
    </mdb-row>
</div>
</div>
</template>

<script>
  import http from "../http-common";
  import nbDRIVER from '@/navbar/nbDRIVER';
  import {mdbContainer, mdbRow, mdbCol , mdbInput,mdbBtn} from 'mdbvue';
  export default {
    name: "RepairRecord",
    components: {
      mdbContainer,
      mdbRow,
      mdbCol,
      mdbInput,
      mdbBtn,
      nbDRIVER
    },

  name:"Report",
  data() {
    return {
      employee: [],
      am_vehicle:[],
      damageType:[],
      employee_id:'null',
      am_vehicle_id:'null',
      damageType_id:'null',
      time:''
    };
  },
  methods:{
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
    getAm_vehicle() {
      http
        .get("/vehicles/")
        .then(response => {
          this.am_vehicle = response.data;
          this.$nextTick();
          console.log(this.am_vehicle);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDamageType() {
      http
        .get("/damageType")
        .then(response => {
          this.damageType = response.data;
          this.$nextTick();
          console.log(this.damageType);
        })
        .catch(e => {
          console.log(e);
        });
    },

    checkList(){
      if(this.employee_id == 'null' || this.vehicle_id == 'null' || this.damageType_id == 'null'){
          alert("กรุณาเลือกข้อมูล");
      }else if(this.time == ''){
          alert("กรุณากรอกข้อมูลจำนวนวัน");
      }else{
          this.saveRepairRecord();
      }
    },
    saveRepairRecord(){
      http
        .post("/RepairRecord/"+
          this.employee_id+
          "/" +this.am_vehicle_id +
          "/" +this.damageType_id +
          "/" +this.time
        )

        .then(response => {
          console.log(response); 
          alert("บันทึกข้อมูลสำเร็จ");
          
        })
        .catch(e => {
          console.log(e);
          alert("บันทึกข้อมูลไม่สำเร็จ เนื่องจาก:"+e+"กรุณาตรวจสอบข้อมูลที่บันทึก");

        });
    }
  },
  mounted(){
    this.getEmployee();
    this.getAm_vehicle();
    this.getDamageType();
  }
};
</script>
  
<style>
  .from-RepairRecord{
    height: 100%;
    margin-top: 80px;
  }
  .bg {
    /* The image used */
    background-image: url("https://thepilotlawyer.com/wp-content/uploads/2018/02/drug-test.jpg");

    /* Full height */
    height: 100%;

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    
  }
</style>