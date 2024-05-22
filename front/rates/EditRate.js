import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditUser() {
  let navigate = useNavigate();

  const { id } = useParams();

  const [r, setRate] = useState({
    data: "",
    from: "",
    to: "",
    rate:""
  });

  const { data, from, to, rate } = r;

  const onInputChange = (e) => {
    setRate({ ...r, [e.target.name]: e.target.value });
  };

  useEffect(() => {
    loadRate();
  }, []);

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/exchangerate/new/${id}`, r);
    navigate("/");
  };

  const loadRate = async () => {
    const result = await axios.get(`http://localhost:8080/exchangerate/${id}`);
    setRate(result.data);
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Edit Rate</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Data" className="form-label">
                Data
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter data"
                name="data"
                value={data}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="From" className="form-label">
                From
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter from"
                name="from"
                value={from}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="To" className="form-label">
                To
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter to"
                name="to"
                value={to}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Rate" className="form-label">
                Rate
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter rate"
                name="rate"
                value={rate}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary" onClick={onSubmit}>
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}