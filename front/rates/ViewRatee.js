
import axios from "axios";
import React, {useEffect, useState} from 'react';
import { Link, useParams } from "react-router-dom";

export default function ViewRatee() {
  const[conv,setConv] = useState([]);

  const [r, setRate] = useState({
    data: "",
    from: "",
    to: "",
    rate:"",
  });
  

  const { id } = useParams();
  const {convId} = useParams();

  useEffect(() => {
    loadRate();
    loadConv(); 
  }, []);

 /* const getConv = async() =>{
    const result = await axios.get("http://localhost:8080/convert/all");
    setConv(result.data);
  };*/

  const loadRate = async()=> {
    const result = await axios.get(`http://localhost:8080/exchangerate/${id}`)
    setRate(result.data);
  };
  const loadConv = async()=>{
    const result = await axios.get(`http://localhost:8080/convert/byrate/${id}`);
    setConv(result.data);
  }

  
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Rate Details</h2>

          <div className="card">
            <div className="card-header">
              Details of rate id : {r.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Data:</b>
                  {r.data}
                </li>
                <li className="list-group-item">
                  <b>From:</b>
                  {r.from}
                </li>
                <li className="list-group-item">
                  <b>To:</b>
                  {r.to}
                </li>
                <li className="list-group-item">
                  <b>Rate:</b>
                  {r.rate}
                </li>
              </ul>
            </div>
           
            <table className='table border shadow'>
              <thread>
                <tr>
                  <th scope="col" className="text-left m-4">amountFrom</th>
                  <th scope="col" className="text-rigth m-4">amountTo</th>
                </tr>
              </thread>
              <tbody>
                {conv.map((c)=>(
                    <tr>
                      <td>{c.amountFrom}</td>
                      <td>{c.amountTo}</td>
                    </tr>
                  ))}
              </tbody>
            </table>
        

          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to Home
          </Link>
          <Link
              className='btn btn-primary mx-2'
              to={`/addConv/${r.id}`}>
                Add conversion
              </Link>
        </div>
      </div>
    </div>
  );
}
