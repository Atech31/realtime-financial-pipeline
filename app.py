import streamlit as st
import requests
import pandas as pd
import time

st.set_page_config(
    page_title="Real-Time Financial Pipeline",
    page_icon="📈",
    layout="wide"
)

st.title("📈 Real-Time Financial Transactions & Analytics Pipeline")
st.markdown("Live streaming trade data from Spring Boot backend and PostgreSQL.")

API_URL = "http://localhost:8080/api/transactions"

# Auto-refresh toggle
refresh_rate = st.sidebar.slider("Refresh Interval (seconds)", 1, 10, 3)
auto_refresh = st.sidebar.checkbox("Auto-Refresh Data", value=True)

def fetch_data():
    try:
        response = requests.get(API_URL)
        if response.status_code == 200:
            return pd.DataFrame(response.json())
    except Exception as e:
        st.error(f"Could not connect to backend API: {e}")
    return pd.DataFrame()

df = fetch_data()

if not df.empty:
    # Metrics Row
    col1, col2, col3 = st.columns(3)
    col1.metric("Total Transactions", len(df))
    col2.metric("Total Volume ($)", f"{df['amount'].sum():,.2f}")
    col3.metric("Unique Symbols", df['symbol'].nunique())

    st.subheader("📊 Live Transaction Feed")
    st.dataframe(df.sort_values(by="id", ascending=False), use_container_width=True)

    st.subheader("📉 Price Trend by Symbol")
    if "symbol" in df.columns and "price" in df.columns:
        chart_data = df.pivot_table(index="id", columns="symbol", values="price")
        st.line_chart(chart_data)
else:
    st.warning("No transactions found yet. Ensure your Spring Boot backend is running and connected to PostgreSQL.")

if auto_refresh:
    time.sleep(refresh_rate)
    st.rerun()
