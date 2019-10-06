<template>
<div><nbER></nbER>
<div class="main">
  <div class="card">
  <h5 class="card-header info-color white-text text-center py-4">
  <strong>ระบบบันทึกการจัดซื้อครุภัณฑ์</strong>
  </h5>
  <!--Card content-->
    <div class="card-body px-lg-5 pt-0">

      <div class="form-row">
        <div class="col">
          <div class="md-form text-left">
          <p>
            <span class="badge badge-light" style="font-size:18px;">เลือกรหัสพนักงาน</span>
            <span class="badge badge-danger" style="font-size:10px;margin-left:8px;">Important</span>
          </p>
          <select class="browser-default custom-select" name = "employee_id" 
          v-model="employee_id" 
          @change="onChange($event)" 
          :required="true" >
             <option :value="null" 
             disabled 
             :selected="true">เลือกรหัสพนักงาน</option>

             <option 
              v-for="emp in employees" 
              v-bind:key="emp.id"
              v-bind:value="emp.id"
              >id::{{ emp.id }}  ||  {{ emp.name }}</option>
          </select>
          </div>
        </div>
        <div class="col">
          <div class="md-form text-left">
          <p>
            <span class="badge badge-light" style="font-size:18px;">เลือกรหัสรถพยาบาล</span>
            <span class="badge badge-danger" style="font-size:10px;margin-left:8px;">Important</span>
          </p>
          <select class="browser-default custom-select" name = "amvehicle_id" v-model="amvehicle_id" @change="onChange($event)"  :required="true"> 
          <option :value="null" disabled :selected="true">เลือกรหัสรถพยาบาล</option>
          <option 
              v-for="vehi in vehicles" 
              v-bind:key="vehi.id"
              v-bind:value="vehi.id"
              >{{ vehi.id }}
          </option>
          </select>
          </div>
        </div>
      </div>

      <div class="md-form mt-0">
        <div class="md-form text-left">
        <p>
            <span class="badge badge-light" style="font-size:18px;">เลือกประเภทครุภัณฑ์</span>
            <span class="badge badge-danger" style="font-size:10px;margin-left:8px;">Important</span>
          </p>
        <select class="browser-default custom-select" aria-placeholder="เลือกประเภทครุภัณฑ์" name = "ptype_id" v-model="ptype_id" @change="onChange($event)"  :required="true">
          <option  :value="null" disabled :selected="true">เลือกประเภทครุภัณฑ์</option>
          <option 
              v-for="pty in purchaseType" 
              v-bind:key="pty.id"
              v-bind:value="pty.id"
              >{{ pty.type }}
          </option>
        </select>
        </div>
      </div>
      <div class="md-form text-right">
          <span class="badge badge-danger">Important</span>
        <input type="text" name = "pname" v-model="pname" class="form-control" @change="onChange($event)"  placeholder="ชื่อครุภัณฑ์">
      </div>
      <div class="md-form text-right">
          <span class="badge badge-danger">Important</span>
        <input type="text" name ="pdate" v-model="pdate" class="form-control" @change="onChange($event)"  placeholder="วัน/เดือน/ปี ที่ซื้อ">
      </div>
      <center>
      <div v-if="valid">
        <button class="btn btn-indigo" style="width:40%" @click="saveSupplyProcurement">บันทึก</button>
      </div>
      <div v-else-if="!valid">
        <button class="btn btn-blue-grey disabled" style="width:40%">บันทึก</button>
      </div>
      </center>
    </div>
  </div>
</div>
</div>
</template>

<script>
import axi from "../http-common";
import nbER from '@/navbar/nbER';
export default {
  name: 'SupplyProcurement',
  components: {
    nbER
  },
  data () {
    return {
      employees:[],
      vehicles:[],
      purchaseType:[],  
        employee_id: "",
        amvehicle_id: "",
        ptype_id: "",
        pname: "",
        pdate: "",
      valid: false,
    };
  },
  methods:{
    getEmployees() {
      axi
        .get("/employees/")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
    },
    getVehicles() {
      axi
        .get("/vehicles/")
        .then(response => {
          this.vehicles = response.data;
          console.log(response.data);
        })
    },
    getPurchaseType() {
      axi
        .get("/supplyProcurement/purchaseType")
        .then(response => {
          this.purchaseType = response.data;
          console.log(response.data);
        })
    },
    onChange(event) {
      if(this.employee_id && this.amvehicle_id && this.ptype_id && this.pname && this.pdate)
        this.valid = true;
      else
        this.valid = false;
    },
    saveSupplyProcurement(){
      var pdate_split = this.pdate.split("/");
        axi
          .post(
            "/supplyProcurement/" +
              this.employee_id +
              "/" +
              this.amvehicle_id +
              "/" +
              this.ptype_id +
              "/" +
              this.pname+
              "/"+
              pdate_split[0]+"@"+pdate_split[1]+"@"+pdate_split[2]
              ,this.SupplyProcurement
          )
          .then(response => {
            console.log(response);
            location.href="http://localhost:9000/supplyProcurement/"
            //this.$router.push("/view");
          })
      },
      refreshList() {
        this.getEmployees();
        this.getVideos();
        this.getRentalTypes();
      },
  },
  mounted(){
    this.getEmployees(),
    this.getVehicles(),
    this.getPurchaseType()
  }
}
</script>

<style>
  .main {
    width:1024px;
    height: 500px;
    margin: 100px auto;
    position: relative;
  }
</style>