import React, {useEffect, useState} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const ListComponent= () =>  {

    const [rates, setRates] = useState([])

    //const {id} = useParams();


    useEffect(() => {
        loadRates();
    }, [])

    const loadRates = async()=>{
        const result = await axios.get("http://localhost:8080/exchangerate/all");
        setRates(result.data);
    }

    const deleteRate = async (id) => {
        await axios.delete(`http://localhost:8080/exchangerate/${id}`);
        loadRates();
      };
    
    return (
        <div className='container'>
            <div className='py-4'>
            <h2 className='text-center'>List of rates</h2>
            <Link 
            className='btn btn-primary mx-2 text-left'
            to={"/add"}>
                Add
            </Link>
        
                <table className='table border shadow'>
                    <thead>
                        <tr>
                            <th scope="col">S.N</th>
                            <th scope="col">Data</th>
                            <th scope="col">From</th>
                            <th scope="col">To</th>
                            <th scope="col">Rate</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {rates.map((rate,index)=>(
                            <tr>
                                <th scope= "row" key={index}>
                                    {index+1}
                                </th>
                            
                                <td>{rate.data}</td>
                                <td>{rate.from}</td>
                                <td>{rate.to}</td>
                                <td>{rate.rate}</td>
                                <td>
                                    <Link 
                                    className="btn btn-primary mx-2"
                                    to={`/update/${rate.id}`}>
                                        Edit
                                    </Link>
                                    <Link
                                    className='btn btn-primary mx-2'
                                    to={`/view/${rate.id}`}>
                                        View
                                    </Link>
                                    <button
                                    className='btn btn-danger mx-2'
                                    onClick={() =>deleteRate(rate.id)}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
   /* return (
        <div className='container'>
            <h2 className='text-center'>List of rates</h2>
            <button className='btn btn-primary mb-2' onClick={addNewRate}>Add Rate</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>DATA</th>
                        <th>FROM</th>
                        <th>TO</th>
                        <th>RATE</th>
                        <th>ACTIONS</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        /*rates.map((rate) =>{
                            return(
                        <tr key={rate.id}>
                            <td>{rate.data}</td>
                            <td>{rate.from}</td>
                            <td>{rate.to}</td>
                            <td>{rate.rate}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => updateExchangeRate(rate.id)}>Update</button>
                                <button className='btn btn-info' onClick={() => removeRate(rate.id)}>
                                    style ={{marginLeft: '10px'}}
                                    Delete</button>
                            </td>
                        </tr>)})*/
                        /*JSON.stringify((rates)=>{
                            return(
                                rates.map((rate) =>{
                                    return(
                                <tr key={rate.id}>
                                    <td>{rate.data}</td>
                                    <td>{rate.from}</td>
                                    <td>{rate.to}</td>
                                    <td>{rate.rate}</td>
                                    <td>
                                        <button className='btn btn-info' onClick={() => updateExchangeRate(rate.id)}>Update</button>
                                        <button className='btn btn-info' onClick={() => removeRate(rate.id)}>
                                            style ={{marginLeft: '10px'}}
                                            Delete</button>
                                    </td>
                                </tr>)}))
                        })
                        this.state.rates.map(
                            rate => 
                            <tr key = {rate.id}>
                                 <td> {rate.date} </td>   
                                 <td> {rate.from}</td>
                                 <td> {rate.to}</td>
                                 <td> {rate.rate}</td>
                                 <td>
                                     <button onClick={ () => this.editRate(rate.id)} className="btn btn-info">Update </button>
                                     <button style={{marginLeft: "10px"}} onClick={ () => this.deleteRate(rate.id)} className="btn btn-danger">Delete </button>
                                     <button style={{marginLeft: "10px"}} onClick={ () => this.viewRate(rate.id)} className="btn btn-info">View </button>
                                 </td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    )*/
}

export default ListComponent