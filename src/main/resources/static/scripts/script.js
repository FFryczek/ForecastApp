async function fetchMeteo(mountainName) {
  try {
    const response = await fetch(`/meteo/${encodeURIComponent(mountainName)}`);
    if (!response.ok) throw new Error("Unable to get forecast details");
    const data = await response.json();

    let html = `
      <div class="card">
        <h2>${mountainName}</h2>
        <p><strong>Elevation:</strong> ${data.elevation} m</p>
        <p><strong>Current temperature:</strong> ${data.current.temperature_2m}°C</p>
        <p><strong>Current RH:</strong> ${data.current.relative_humidity_2m}%</p>
        <p><strong>Rain now:</strong> ${data.current.rain} mm</p>
        <p><strong>Showers now:</strong> ${data.current.showers} mm</p>
        <p><strong>Snowfall now:</strong> ${data.current.snowfall} mm</p>
        <h3>Daily forecast</h3>
        <table>
          <thead>
            <tr>
              <th>Date</th>
              <th>Min temp (°C)</th>
              <th>Max temp (°C)</th>
              <th>Rain sum (mm)</th>
              <th>Showers sum (mm)</th>
              <th>Snowfall sum (mm)</th>
              <th>Details</th>
            </tr>
          </thead>
          <tbody>
            ${data.daily.date.map((date, i) => `
              <tr>
                <td>${date}</td>
                <td>${data.daily.temperature_2m_min[i]}</td>
                <td>${data.daily.temperature_2m_max[i]}</td>
                <td>${data.daily.rain_sum[i]}</td>
                <td>${data.daily.showers_sum[i]}</td>
                <td>${data.daily.snowfall_sum[i]}</td>
                <td><button onclick="goToDaily('${mountainName}', '${date}')">Show more details</button></td>
              </tr>
            `).join('')}
          </tbody>
        </table>
      </div>
    `;
    document.getElementById('result').innerHTML = html;
  } catch (error) {
    console.error('Fetch error:', error);
    document.getElementById('result').innerHTML = `<p style="color: red;">Could not load data. Try again later.</p>`;
  }
}

function goToDaily(mountainName, date) {
  const url = `/daily.html?mountain=${encodeURIComponent(mountainName)}&date=${encodeURIComponent(date)}`;
  window.location.href = url;
}
