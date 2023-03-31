<template>
  <div id="home-page">
    <h2 class="table-title">Users</h2>
    <table>
      <tr>
        <th>Name</th>
        <th>Creation date</th>
      </tr>
      <tr v-for="p in persons">
        <td>{{ p.name }}</td>
        <td>{{ p.creationDate }}</td>
      </tr>
      <!-- <tr>
        <td>John</td>
        <td>2023/03/31</td>
      </tr> -->
    </table>
    <h2>New User</h2>
    <input type="text" placeholder="Name" v-model="newPersonName">
    <input type="password" placeholder="Password" v-model="newPersonPassword">
    <button v-bind:disabled="createUserButtonDisabled" @click="createUser()">Create</button>
    <p class="error">{{ errorMsg }}</p>
  </div>
</template>

<script>
import axios from 'axios';
const config = require('../../config');
const frontendUrl = config.dev.host + ':' + config.dev.port;
const axiosClient = axios.create({
  // Note the baseURL, not baseUrl
  baseURL: config.dev.backendBaseUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
});

export default {
  name: 'Home',
  data() {
    return {
      persons: [
        {name: 'John', creationDate: '2023/03/31'},
        {name: 'Alice', creationDate: '2022/12/31'},
      ],
      newPersonName: '',
      newPersonPassword: '',
      errorMsg: '',
    };
  },
  created() {
    axiosClient.get('/person')
      .then((response) => {
        console.log(response);
        this.persons = response.data;
      })
      .catch((err) => {
        console.log(err);
        this.errorMsg = err;
      })
  },
  methods: {
    createUser() {
      if (this.newPersonName === 'C3PO') {
        this.errorMsg = 'Droids are not allowed.';
        return;
      }
      
      const request = {name: this.newPersonName, password: this.newPersonPassword};
      axiosClient.post('/person', request)
        .then((response) => {
          const person = response.data;
          this.persons.push(person);
          this.newPersonName = '';
          this.newPersonPassword = '';
          this.errorMsg = '';
        })
        .catch((err) => {
          this.errorMsg = `Failed to create: ${err.response.data}`;
        })
    }
  },
  computed: {
    createUserButtonDisabled() {
      return !this.newPersonName.trim() || !this.newPersonPassword.trim();
    }
  }
}
</script>

<style>
td, th {
  border: 1px solid black;
  padding: 0.5em;
}

#home-page {
  display: flex;
  flex-direction: column;
  padding: 5%;
  align-items: stretch;
}

.error {
  color: red;
}
</style>
