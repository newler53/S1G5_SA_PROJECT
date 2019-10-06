<template>
<div><nbER></nbER>
<div class="main">
  <div class="card">
  <h3 class="card-header success-color white-text text-center py-7">
  <strong><u>ระบบบันทึกการเข้าออกของพนักงาน</u></strong>
  </h3>
  <!--Card content-->
    <div class="card-body px-lg-5 pt-0">

      <div class="form-col">
        <div class="col">
          <div class="md-form text-left">
          <p>
            <span class="badge badge-light" style="font-size:18px;">เลือกรหัสพนักงาน</span>
          </p>
         <select class="browser-default custom-select" name = "employee_id" 
          v-model="employee_id" 
          @change="onChange($event)" 
          :required="true" > 
             <option :value="null" 
             disabled 
             :selected="true"></option>
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
            <span class="badge badge-light" style="font-size:18px;">เลือกรถพยาบาล</span>
          </p>
          <select class="browser-default custom-select" name = "amvehicle_id" v-model="amvehicle_id" @change="onChange($event)"  :required="true"> 
          <option :value="null" disabled :selected="true">เลือกรถพยาบาล</option>
          <option 
              v-for="vehi in vehicles" 
              v-bind:key="vehi.id"
              v-bind:value="vehi.id"
              >{{ vehi.am_Code}}
          </option>
          </select>
          </div>
        </div>
      </div>

      <div class="col">
      <div class="md-form mt-0">
        <div class="md-form text-left">
        <p>
            <span class="badge badge-light" style="font-size:18px;">เลือกการเข้าออกงาน</span>
          </p>
        <select class="browser-default custom-select" name = "chooseStartGetoff_id" v-model="chooseStartGetoff_id" @change="onChange($event)"  :required="true">
         <option :value="null" disabled :selected="true">เลือกการเข้าออกงาน</option>
          <option 
              v-for="cgw in startGetoffWork" 
              v-bind:key="cgw.id"
              v-bind:value="cgw.id"
              >{{ cgw.chooseStartGetoff}}
          </option>
        </select>
        </div>
      </div>
      <div class="md-form text-left">
      <p><span class="badge badge-light" style="font-size:18px;">เวลา</span></p>
        <input type="text" id="nowtime" class="form-control" disabled>
      </div>
      </div>
      
      <center>
      <div v-if="valid">
        <button class="btn btn-indigo" style="width:40%" @click="saveWorksheet">บันทึกข้อมูล</button>
      </div>
      <div v-else-if="!valid">
        <button class="btn btn-blue-grey disabled" style="width:40%">บันทึกข้อมูล</button>
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
  name: 'Worksheet',
  components: {
    nbER
  },
  data () {
    return {
      employees:[],
      vehicles:[],
      startGetoffWork:[],
        employee_id: "",
        amvehicle_id: "",
        chooseStartGetoff_id: "",
        
      valid: false,
    };
  },
  methods:{
    getEmployees() {
      axi
        .get("/employees/")
        .then(response => {
          this.employees = response.data;
        })
    },
    getVehicles() {
      axi
        .get("/vehicles/")
        .then(response => {
          this.vehicles = response.data;
        })
    },
    getStartGetoffWork() {
      axi
        .get("/StartGetoffWork/")
        .then(response => {
          this.startGetoffWork = response.data;
        })
    },
    onChange(event) {
      if(this.employee_id && this.amvehicle_id && this.chooseStartGetoff_id)
        this.valid = true;
      else
        this.valid = false;
    },
    getNowTime(){
      var xTime = new Date();
      document.getElementById("nowtime").value = xTime.getHours() + ":" + xTime.getMinutes() + ":" + xTime.getSeconds();
    },
    saveWorksheet(){
        axi
          .post(
            "/Worksheet/" +
              this.employee_id +
              "/" +
              this.amvehicle_id +
              "/" +
              this.chooseStartGetoff_id
              ,this.Worksheet
              
          )
          .then(response => {
            alert("Save successfully");
            location.href="http://localhost:8080/#/Worksheet"
          })
      },
  },
  mounted(){
    this.getEmployees(),
    this.getVehicles(),
    this.getStartGetoffWork(),
    this.getNowTime()
  }
}
</script>

<style >
  .main {
    width:1024px;
    height: 500px;
    margin: 100px auto;
    position: relative;
  }
</style>